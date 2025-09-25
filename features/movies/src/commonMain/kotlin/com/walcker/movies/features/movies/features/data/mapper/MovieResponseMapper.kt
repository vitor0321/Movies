package com.walcker.movies.features.movies.features.data.mapper

import com.walcker.movies.features.movies.features.data.mapper.CastMemberResponseMapper.toDomain
import com.walcker.movies.features.movies.features.data.mapper.GenreResponseMapper.toDomain
import com.walcker.movies.features.movies.features.data.models.CastMemberResponse
import com.walcker.movies.features.movies.features.data.models.MovieResponse
import com.walcker.movies.features.movies.features.data.network.HttpConfig
import com.walcker.movies.features.movies.features.domain.models.ImageSize
import com.walcker.movies.features.movies.features.domain.models.Movie
import com.walcker.movies.features.movies.handle.formatRating
import kotlinx.collections.immutable.toImmutableList

private const val DEPARTMENT = "Acting"
internal const val MAX_CAST_MEMBERS = 20

internal object MovieResponseMapper {
    fun MovieResponse.toDomain(
        castMembersResponse: List<CastMemberResponse>? = null,
        moviesTrailerYouTubeKey: String? = null,
        imageSize: ImageSize = ImageSize.SMALL,
    ): Movie =
        Movie(
            id = id,
            title = title,
            overview = overview,
            posterUrl = HttpConfig.IMAGE_BASE_URL.value + "/${imageSize.size}/" + posterPath.orEmpty(),
            genres = genres?.map { it.toDomain() }?.toImmutableList(),
            year = releaseDate.year.toString(),
            duration = getDurationInHoursAndMinutes(),
            rating = voteAverage.formatRating(),
            castMembers = castMembersResponse
                ?.filter { it.department == DEPARTMENT }
                ?.take(n = MAX_CAST_MEMBERS)
                ?.map { it.toDomain() }?.toImmutableList(),
            moviesTrailerYouTubeKey = moviesTrailerYouTubeKey,
        )

    private fun MovieResponse.getDurationInHoursAndMinutes(): String? =
        runtime?.let { runtimeMinutes ->
            val hours = runtimeMinutes / 60
            val minutes = runtimeMinutes % 60

            buildString {
                if (hours > 0) append("${hours}h ")
                if (minutes > 0) append("${minutes}min")
            }
        }
}