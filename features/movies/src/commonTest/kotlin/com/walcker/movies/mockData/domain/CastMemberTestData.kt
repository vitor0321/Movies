package com.walcker.movies.mockData.domain

import com.walcker.movies.features.movies.features.domain.models.CastMember
import kotlinx.collections.immutable.persistentListOf

internal val castMember1TestData = CastMember(
    id = 287,
    mainRole = "Acting",
    name = "Brad Pitt",
    character = "Tyler Durden",
    profileUrl = "https://image.tmdb.org/t/p/w154//cckcYc2v0yh1tc9QjRelptcOBko.jpg"
)

internal val castMember2TestData = CastMember(
    id = 819,
    mainRole = "Acting",
    name = "Edward Norton",
    character = "The Narrator",
    profileUrl = "https://image.tmdb.org/t/p/w154//5XBzD5WuTyVQZeS4VI25z2moMeY.jpg"
)

internal val castMemberListTestData = persistentListOf(
    castMember1TestData,
    castMember2TestData
)