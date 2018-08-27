package id.aasumitro.dicodingbeginner.utlil

import android.content.Context
import android.content.Intent
import android.graphics.Color.WHITE
import android.net.Uri
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences
import id.aasumitro.dicodingbeginner.data.preferences.SharedPreferences.get
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import android.support.customtabs.CustomTabsIntent
import id.aasumitro.dicodingbeginner.R


/**
 * Created by Agus Adhi Sumitro on 25/08/2018.
 * https://aasumitro.id
 * aasumitro@gmail.com
 */

fun dateHelper (date: String?) : String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val fullDateUpdated = dateFormat.parse(date)
    val previousCal = Calendar.getInstance()
    previousCal.time = fullDateUpdated
    val currentCal = Calendar.getInstance()
    val difference = currentCal.timeInMillis - previousCal.timeInMillis
    val second = TimeUnit.MILLISECONDS.toSeconds(difference)
    val minute = second / 60
    val hour = second / 3600
    val day = second / 86400
    val week = second / 86400*7
    val month = second / 86400*31
    return when {
        second <= 60 -> "few seconds ago"
        second in 60..120 -> "1 minute ago"
        second in 120..3600 -> "$minute minutes ago"
        second in 3600..86400 -> "$hour hours ago"
        second in 86400..2628000 -> "$day days ago"
        second in (86400*7)..(86400*7)*4 -> "$week weeks ago"
        second in (86400*31)..(86400*31)*11 -> "$month months ago"
        else -> "Published more than a year ago"
    }
}

fun source (context: Context) : String? {
    val mPrefs = SharedPreferences
            .defaultPrefs(context.applicationContext)
    return mPrefs[AppConst.SOURCE_KEY]
}

fun customTabs (context: Context, url: String) {
    val uri = Uri.parse(url)
    val intentBuilder = CustomTabsIntent.Builder()
    intentBuilder.setToolbarColor(WHITE)
    intentBuilder.setStartAnimations(context, R.anim.slide_in_top, 0)
    intentBuilder.setExitAnimations(context, 0, R.anim.slide_out_top)
    intentBuilder.setShowTitle(true)
    val customTabsIntent = intentBuilder.build()
    customTabsIntent.intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    customTabsIntent.launchUrl(context, uri)
}