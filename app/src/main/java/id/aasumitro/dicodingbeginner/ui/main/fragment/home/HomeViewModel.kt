package id.aasumitro.dicodingbeginner.ui.main.fragment.home

import android.arch.lifecycle.ViewModel
import android.util.Log
import id.aasumitro.dicodingbeginner.data.model.Everything
import id.aasumitro.dicodingbeginner.data.model.Headline
import id.aasumitro.dicodingbeginner.data.remote.ApiClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class HomeViewModel : ViewModel() {

    private var mNavigator: HomeNavigation? = null
    private var mApiClient: ApiClient? = null

    var listHorizontal = ArrayList<Headline>()
    var listVertical = ArrayList<Everything>()

    fun homeViewModel(navigation: HomeNavigation,
                       apiClient: ApiClient) {
        this.mNavigator = navigation
        this.mApiClient = apiClient
    }

    fun getHorizontalList(source: String, key: String) {
        mApiClient!!
                .apiServices()
                .headlinesNews(source, key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ onSuccess ->
                    mNavigator!!.onLoading(true)
                    if (onSuccess.status === "error") {
                        mNavigator!!.onSuccess(false, onSuccess.code, onSuccess.message)
                        mNavigator!!.onLoading(false)
                    } else {
                        listHorizontal = onSuccess.articles as ArrayList<Headline>
                        mNavigator!!.initHorizontalAdapter().let {
                            mNavigator!!.onLoading(false)
                        }
                    }
                }, { onError ->
                    onError.printStackTrace()
                    onError.let {
                        mNavigator!!.onLoading(false)
                    }
                })
    }

    fun getVerticalList(source: String, key: String) {
        mApiClient!!
                .apiServices()
                .everythingNews(source, key)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe  ({ onSuccess ->
                    mNavigator!!.onLoading(true)
                    if (onSuccess.status === "error") {
                        mNavigator!!.onSuccess(false, onSuccess.code, onSuccess.message)
                        mNavigator!!.onLoading(false)
                    } else {
                        listVertical = onSuccess.articles as ArrayList<Everything>
                        mNavigator!!.initVerticalAdapter().let {
                            mNavigator!!.onLoading(false)
                        }
                    }
                    Log.d("DataFromServer", onSuccess.toString())
                }, { onError ->
                    onError.printStackTrace()
                    onError.let {
                        mNavigator!!.onLoading(false)
                    }
                })
    }

}