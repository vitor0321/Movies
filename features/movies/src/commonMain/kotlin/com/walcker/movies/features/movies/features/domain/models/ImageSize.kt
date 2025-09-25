package com.walcker.movies.features.movies.features.domain.models

internal enum class ImageSize(
    val size: String
) {
    X_SMALL("w154"),
    SMALL("w185"),
    MEDIUM("w342"),
    LARGE("w500"),
    X_LARGE("w780"),
    ORIGINAL("original")
}