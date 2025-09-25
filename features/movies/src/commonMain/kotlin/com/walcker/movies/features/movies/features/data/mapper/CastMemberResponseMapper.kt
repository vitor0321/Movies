package com.walcker.movies.features.movies.features.data.mapper

import com.walcker.movies.features.movies.features.data.models.CastMemberResponse
import com.walcker.movies.features.movies.features.data.network.HttpConfig
import com.walcker.movies.features.movies.features.domain.models.CastMember
import com.walcker.movies.features.movies.features.domain.models.ImageSize

internal object CastMemberResponseMapper {
    fun CastMemberResponse.toDomain() =
        CastMember(
            id = id,
            name = name,
            character = character,
            mainRole = department,
            profileUrl = "${HttpConfig.IMAGE_BASE_URL.value}/${ImageSize.X_SMALL.size}/${profilePictureUrl}",
        )
}