package com.walcker.movies.features.movies.features.data.models

import kotlinx.serialization.Serializable

@Serializable
internal data class VideoResponse(
    val id: Int,
    val results: List<VideoItemResponse>
)

@Serializable
internal data class VideoItemResponse(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String,
    val official: Boolean,
)