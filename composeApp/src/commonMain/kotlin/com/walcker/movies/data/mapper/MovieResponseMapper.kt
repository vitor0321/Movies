package com.walcker.movies.data.mapper

import com.walcker.movies.data.models.MovieResponse
import com.walcker.movies.data.network.HttpConfig
import com.walcker.movies.domain.models.Movie

internal object MovieResponseMapper {
    fun MovieResponse.toDomain(): Movie =
        Movie(
            id = id,
            title = title,
            overview = overview,
            posterUrl = "${HttpConfig.IMAGE_SMALL_BASE_URL.value}${posterPath}",
        )
}