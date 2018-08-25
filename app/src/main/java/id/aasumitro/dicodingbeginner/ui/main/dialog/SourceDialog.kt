package id.aasumitro.dicodingbeginner.ui.main.dialog

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.support.v4.os.ResultReceiver
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences.set
import id.aasumitro.dicodingbeginner.ui.main.activity.MainActivity
import id.aasumitro.dicodingbeginner.utlil.AppConst
import kotlinx.android.synthetic.main.dialog_source.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class SourceDialog : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_source)
        onSavePressed()
    }

    @SuppressLint("RestrictedApi")
    private fun onSavePressed() {
        btn_save.setOnClickListener {
            val mPrefs = SharedPreferences.defaultPrefs(this)
            mPrefs[AppConst.SOURCE_KEY] = spinner.selectedItem.toString()
            toast(spinner.selectedItem.toString() + " Selected")
            finish()
            (intent.getParcelableExtra("finisher")
                    as ResultReceiver)
                    .send(1, Bundle())
            startActivity<MainActivity>()
        }
    }

}