package com.walcker.movies.features.movies.features.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class CreditsResponse(
    val id: Int,
    val cast: List<CastMemberResponse>,
)

@Serializable
internal data class CastMemberResponse(
    val id: Int,
    @SerialName("known_for_department")
    val department: String,
    val name: String,
    val character: String,
    @SerialName("profile_path")
    val profilePictureUrl: String? = null,
)
