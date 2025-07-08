package com.walcker.movies.features.data.mapper

import com.walcker.movies.features.data.models.MovieResponse
import com.walcker.movies.features.data.network.HttpConfig
import com.walcker.movies.features.domain.models.Movie

internal object MovieResponseMapper {
    fun MovieResponse.toDomain(): Movie =
        Movie(
            id = id,
            title = title,
            overview = overview,
            posterUrl = "${HttpConfig.IMAGE_SMALL_BASE_URL.value}${posterPath}",
        )
}