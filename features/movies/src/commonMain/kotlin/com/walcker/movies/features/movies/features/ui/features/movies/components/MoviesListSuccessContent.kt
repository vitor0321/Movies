package com.walcker.movies.features.movies.features.ui.features.movies.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.movies.features.movies.strings.features.MoviesListStrings
import com.walcker.movies.features.movies.strings.features.moviesListStringsPt
import com.walcker.movies.features.movies.theme.MoviesAppTheme
import com.walcker.movies.features.movies.features.ui.components.MovieSection
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MoviesListSuccessContent(
    strings: MoviesListStrings,
    movies: ImmutableList<MovieSection>,
    onPosterClick: (movieId: Int) -> Unit,
    onLoadMore: (MovieSection.SectionType) -> Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        items(items = movies) { movieSection ->
            if (movieSection.sectionType == MovieSection.SectionType.HIGHLIGHT)
                HeaderSuccessContent(
                    movies = movieSection.movies,
                    onPosterClick = onPosterClick,
                )

            if (movieSection.sectionType != MovieSection.SectionType.HIGHLIGHT)
                MovieSection(
                    title = movieSection.sectionType.title(strings),
                    movies = movieSection.movies,
                    onPosterClick = onPosterClick,
                    onLoadMore = { onLoadMore(movieSection.sectionType) },
                )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme(isDarkTheme = false) {
        MoviesListSuccessContent(
            strings = moviesListStringsPt,
            movies = movieSectionTestData,
            onPosterClick = {},
            onLoadMore = {}
        )
    }
}