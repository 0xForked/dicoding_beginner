package id.aasumitro.dicodingbeginner.ui.splash

import android.arch.lifecycle.ViewModel
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences
import id.aasumitro.dicodingbeginner.utlil.AppConst
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class SplashViewModel : ViewModel() {

    private var mNavigation: SplashNavigation? = null

    fun splashViewModel(navigator: SplashNavigation) {
        this.mNavigation = navigator
    }

    fun startTask() {
        Observable.interval(5, TimeUnit.SECONDS)
                .take(1)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { task() }
    }

    private fun task() {
        mNavigation?.openMainActivity()
    }
}