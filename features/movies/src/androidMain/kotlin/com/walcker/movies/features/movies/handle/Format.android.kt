package com.walcker.movies.features.movies.handle

import java.util.Locale

actual fun Double.formatRating(): String =
    String
        .format(Locale.getDefault(), "%.1f", this)
        .replace(',', '.')