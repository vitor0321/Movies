package com.walcker.movies.features.data.mapper

import com.walcker.movies.features.data.models.GenreResponse
import com.walcker.movies.features.domain.models.Genre

internal object GenreResponseMapper {
    fun GenreResponse.toDomain() =
        Genre(
            id = id,
            name = name
        )
}