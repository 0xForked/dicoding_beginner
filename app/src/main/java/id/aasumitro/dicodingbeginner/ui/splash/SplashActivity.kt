package id.aasumitro.dicodingbeginner.ui.splash

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences.defaultPrefs
import id.aasumitro.dicodingbeginner.ui.main.activity.MainActivity
import org.jetbrains.anko.startActivity
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences.set
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences.get
import id.aasumitro.dicodingbeginner.utlil.AppConst

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class SplashActivity : AppCompatActivity(), SplashNavigation {

    private val mDefaultSource = "bbc-news"

    private val mViewModel: SplashViewModel by lazy {
        ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val mPrefs = defaultPrefs(this)
        val mSource: String? = mPrefs[AppConst.SOURCE_KEY]
        if (mSource === null) mPrefs[AppConst.SOURCE_KEY] = mDefaultSource

        mViewModel.splashViewModel(this)
        mViewModel.startTask()

    }

    override fun openMainActivity() {
        startActivity<MainActivity>()
        finish()
    }

}
