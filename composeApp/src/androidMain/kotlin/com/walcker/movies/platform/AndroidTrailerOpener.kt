package com.walcker.movies.platform

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class AndroidTrailerOpener(private val context: Context) : TrailerOpener {
    override fun openTrailer(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}
