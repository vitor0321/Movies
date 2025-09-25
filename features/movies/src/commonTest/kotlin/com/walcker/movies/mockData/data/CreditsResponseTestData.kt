package com.walcker.movies.mockData.data

import com.walcker.movies.features.movies.features.data.models.CastMemberResponse
import com.walcker.movies.features.movies.features.data.models.CreditsResponse

internal val castMemberResponse1TestData = CastMemberResponse(
    id = 287,
    department = "Acting",
    name = "Brad Pitt",
    character = "Tyler Durden",
    profilePictureUrl = "/cckcYc2v0yh1tc9QjRelptcOBko.jpg"
)

internal val castMemberResponse2TestData = CastMemberResponse(
    id = 819,
    department = "Acting",
    name = "Edward Norton",
    character = "The Narrator",
    profilePictureUrl = "/5XBzD5WuTyVQZeS4VI25z2moMeY.jpg"
)

internal val creditsResponseTestData = CreditsResponse(
    id = 550,
    cast = listOf(
        castMemberResponse1TestData,
        castMemberResponse2TestData,
    )
)