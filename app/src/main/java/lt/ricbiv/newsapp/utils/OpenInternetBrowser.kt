package lt.ricbiv.newsapp.utils

import android.content.Context
import android.net.Uri
import androidx.core.content.ContextCompat
import androidx.browser.customtabs.CustomTabsIntent
import lt.ricbiv.newsapp.R

object OpenInternetBrowser {
    fun launch(context: Context, url: String) {
        val builder = CustomTabsIntent.Builder()

            .setShowTitle(true)
            .setStartAnimations(
                context,
                R.anim.slide_in,
                R.anim.slide_out
            )
            .setExitAnimations(
                context,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .build()
        builder.launchUrl(context, Uri.parse(url))
    }
}