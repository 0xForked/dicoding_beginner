package id.aasumitro.dicodingbeginner.ui.main.fragment.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences.get
import id.aasumitro.dicodingbeginner.ui.main.activity.MainViewModel
import id.aasumitro.dicodingbeginner.utlil.AppConst
import id.aasumitro.dicodingbeginner.data.remote.ApiClient
import kotlinx.android.synthetic.main.fragment_home.*
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import id.aasumitro.dicodingbeginner.data.model.Headline
import id.aasumitro.dicodingbeginner.data.model.Everything
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.adapter.HorizontalAdapter
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.listener.HorizontalListener
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.listener.VerticalListener
import org.jetbrains.anko.toast
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.adapter.VerticalAdapter

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class HomeFragment : Fragment(), HomeNavigation, HorizontalListener, VerticalListener {

    private var mApiClient = ApiClient()

    private val mViewModelMain: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    private val mViewModelHome: HomeViewModel by lazy {
        ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        savedInstanceState.let {
            mViewModelHome.homeViewModel(this, mApiClient)
            mViewModelHome.getHorizontalList(source()!!, AppConst.API_KEY)
            mViewModelHome.getVerticalList(source()!!, AppConst.API_KEY)
        }

        initHorizontalList()
        initVerticalList()
        onSwipeRefresh()
    }

    override fun onSuccess(isSuccess: Boolean,
                           code: String?,
                           message: String?) {
        if (isSuccess) {
            swipeRefreshLayout.visibility = View.VISIBLE
            layout_error_home.visibility = View.GONE
        } else {
            swipeRefreshLayout.visibility = View.GONE
            layout_error_home.visibility = View.VISIBLE
            error_home_code.text = code
            error_home_message.text = message
        }
    }

    override fun onLoading(isLoading: Boolean) {
        if (isLoading) {
            layout_loading_home.visibility = View.VISIBLE
            swipeRefreshLayout.visibility = View.GONE
        } else {
            layout_loading_home.visibility = View.GONE
            swipeRefreshLayout.visibility = View.VISIBLE
        }
    }

    private fun onSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            mViewModelHome.getHorizontalList(source()!!, AppConst.API_KEY)
            mViewModelHome.getVerticalList(source()!!, AppConst.API_KEY)
        }
    }

    private fun initHorizontalList() {
        rv_horizontal.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager =
                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rv_horizontal.layoutManager = layoutManager
    }

    override fun initHorizontalAdapter() {
        rv_horizontal.adapter =
                HorizontalAdapter(mViewModelHome.listHorizontal, this)
    }

    override fun onHorizontalItemClick(headline: Headline) {
        mViewModelMain.setData(
                headline.author!!,
                headline.title,
                headline.description,
                headline.url,
                headline.urlToImage!!,
                headline.publishedAt!!)
        mViewModelMain.replaceWithDetail()
    }

    private fun initVerticalList() {
        rv_vertical.setHasFixedSize(true)
        val layoutManager : RecyclerView.LayoutManager =
                LinearLayoutManager(activity)
        rv_vertical.layoutManager = layoutManager
        rv_vertical.itemAnimator = DefaultItemAnimator()
    }

    override fun initVerticalAdapter() {
        rv_vertical.adapter =
                VerticalAdapter(mViewModelHome.listVertical, this)
    }

    override fun onVerticalItemClick(everything: Everything) {
        val author =
                if (everything.author === null)
                    source()!!.toUpperCase()
                else
                    everything.author!!
        mViewModelMain.setData(
                author,
                everything.title,
                everything.description,
                everything.url,
                everything.urlToImage!!,
                everything.publishedAt!!)
        mViewModelMain.replaceWithDetail()
    }

    private fun source() : String? {
        val mPrefs = SharedPreferences
                .defaultPrefs(activity!!.applicationContext)
        return mPrefs[AppConst.SOURCE_KEY]
    }

    override fun onPause() {
        super.onPause()
        rv_vertical.recycledViewPool.clear()
        rv_horizontal.recycledViewPool.clear()
    }
}
