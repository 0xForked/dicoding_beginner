package id.aasumitro.dicodingbeginner.ui.main.activity

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import id.aasumitro.dicodingbeginner.R
import id.aasumitro.dicodingbeginner.ui.main.fragment.detail.DetailFragment
import id.aasumitro.dicodingbeginner.ui.main.fragment.home.HomeFragment
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import id.aasumitro.dicodingbeginner.ui.main.dialog.SourceDialog
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent
import android.support.v4.os.ResultReceiver


/**
 * Created by Agus Adhi Sumitro on 24/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

class MainActivity : AppCompatActivity(), MainNavigation {

    // companion object {
    //    @SuppressLint("StaticFieldLeak")
    //    var mThis: Activity? = null
    // }

    private val mViewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // mThis = this
        savedInstanceState.let {
            mViewModel.mainViewModel(this)
            mViewModel.replaceWithHome()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_source -> {
                val finish = Intent(this@MainActivity, SourceDialog::class.java)
                finish.putExtra("finisher", object : ResultReceiver(null) {
                    override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
                        this@MainActivity.finish()
                    }
                })
                startActivityForResult(finish, 1)
            }
            R.id.action_info -> toast("info")
        }
        return true
    }

    private fun replaceFragment (fragment: Fragment,
                                 cleanStack: Boolean = false) {
        val manager = supportFragmentManager.beginTransaction()
        if (cleanStack) clearBackStack()
        manager.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        manager.replace(R.id.fragment_container, fragment)
        manager.addToBackStack(null)
        manager.commit()
    }

    private fun clearBackStack() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 0) {
            val first = manager.getBackStackEntryAt(0)
            manager.popBackStack(first.id,
                    FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
    }

    override fun onBackPressed() {
        val manager = supportFragmentManager
        if (manager.backStackEntryCount > 1) {
            manager.popBackStack()
        } else {
            finish()
        }
    }

    override fun refreshActivity() {
        startActivity<MainActivity>()
        finish()
    }

    override fun replaceWithHome() = replaceFragment(HomeFragment())

    override fun replaceWithDetail() = replaceFragment(DetailFragment())

}
