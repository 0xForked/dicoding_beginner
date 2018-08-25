package id.aasumitro.dicodingbeginner.ui.main.fragment.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.ui.main.activity.MainViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import id.aasumitro.dicodingbeginner.utlil.dateHelper
import id.aasumitro.dicodingbeginner.utlil.source
import id.aasumitro.dicodingbeginner.utlil.AppConst.SIZE
import id.aasumitro.dicodingbeginner.utlil.BitmapTransform
import id.aasumitro.dicodingbeginner.utlil.AppConst.MAX_WIDTH
import id.aasumitro.dicodingbeginner.utlil.AppConst.MAX_HEIGHT
import id.aasumitro.dicodingbeginner.utlil.customTabs

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class DetailFragment : Fragment() {

    private var author: String? = null
    private var title: String? = null
    private var description: String? = null
    private var url: String? = null
    private var urlToImage: String? = null
    private var publishedAt: String? = null

    private val mViewModelMain: MainViewModel by lazy {
        ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        viewModelObserver()
        dataSetter()
    }

    private fun viewModelObserver() {
        author = mViewModelMain.author
        title = mViewModelMain.title
        description = mViewModelMain.description
        url = mViewModelMain.url
        urlToImage = mViewModelMain.urlToImage
        publishedAt = mViewModelMain.publishedAt
    }

    private fun dataSetter() {
        detail_title.text = title
        detail_subtitle.text = getSubtitle(author!!, publishedAt!!)
        getDetailImage(urlToImage!!)
        detail_content.text = description
        onButtonPressed()
    }

    private fun onButtonPressed() {
        facebook_share.setOnClickListener {
            val facebookShareUrl = "http://www.facebook.com/sharer.php?u=$url"
            customTabs(activity!!.applicationContext, facebookShareUrl)
        }

        twitter_share.setOnClickListener {
            val twitterShareUrl = "https://twitter.com/share?text=$title&url=$url"
            customTabs(activity!!.applicationContext, twitterShareUrl)
        }

        btn_source.setOnClickListener {
           customTabs(activity!!.applicationContext, url!!)
        }
    }

    private fun getSubtitle(author: String, publishedAt: String) : String {
        var authorData = author
        val date = dateHelper(publishedAt)
        val bbcNews = "https://www.facebook.com/bbcnews"
        if (author == bbcNews) authorData = source(activity!!.applicationContext)!!
        return "$authorData - $date"
    }

    private fun getDetailImage(imgUrl: String) {
        Picasso.get()
                .load(imgUrl)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image)
                .transform(BitmapTransform(MAX_WIDTH, MAX_HEIGHT))
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .resize(SIZE, SIZE)
                .centerInside()
                .into(detail_image)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        super.onCreateOptionsMenu(menu, inflater)
        menu.findItem(R.id.action_source).isVisible = false
    }

}
