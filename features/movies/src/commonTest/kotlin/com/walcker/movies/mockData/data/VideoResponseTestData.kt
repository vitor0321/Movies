package com.walcker.movies.mockData.data

import com.walcker.movies.features.movies.features.data.models.VideoItemResponse
import com.walcker.movies.features.movies.features.data.models.VideoResponse

internal val videoResponseTestData = VideoResponse(
    id = 550,
    results = listOf(
        VideoItemResponse(
            name = "Fight Club (1999) Trailer - Starring Brad Pitt, Edward Norton, Helena Bonham Carter",
            key = "O-b2VfmmbyA",
            site = "YouTube",
            type = "Trailer",
            official = false,
            id = "639d5326be6d88007f170f44",
        ),
        VideoItemResponse(
            name = "#TBT Trailer",
            key = "BdJKm16Co6M",
            site = "YouTube",
            type = "Trailer",
            official = true,
            id = "5c9294240e0a267cd516835f",
        )
    )
)