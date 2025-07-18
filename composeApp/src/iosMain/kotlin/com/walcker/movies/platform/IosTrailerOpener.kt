package com.walcker.movies.platform

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

internal class IosTrailerOpener : TrailerOpener {
    override fun openTrailer(url: String) {
        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl != null) {
            UIApplication.sharedApplication.openURL(nsUrl)
        }
    }
}
