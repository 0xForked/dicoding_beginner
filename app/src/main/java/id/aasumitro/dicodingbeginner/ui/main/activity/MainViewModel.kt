package id.aasumitro.dicodingbeginner.ui.main.activity

import android.arch.lifecycle.ViewModel
import id.aasumitro.dicodingbeginner.ui.main.activity.MainNavigation

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class MainViewModel : ViewModel() {
    private var mNavigator: MainNavigation? = null

    var author: String? = null
    var title: String? = null
    var description: String? = null
    var url: String? = null
    var urlToImage: String? = null
    var publishedAt: String? = null

    fun mainViewModel(navigation: MainNavigation) {
        this.mNavigator = navigation
    }

    fun replaceWithHome() = mNavigator?.replaceWithHome()

    fun replaceWithDetail() = mNavigator?.replaceWithDetail()

    fun setData(author: String, title: String, description: String,
                 url: String, urlToImage: String, publishedAt: String) {
        this.author = author
        this.title = title
        this.description = description
        this.url = url
        this.urlToImage = urlToImage
        this.publishedAt = publishedAt
    }
}