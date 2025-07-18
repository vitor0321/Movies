package com.walcker.movies.features.ui.features.movies

import com.walcker.movies.features.domain.models.MovieSection
import com.walcker.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.mockFakes.FakeMoviesRepository
import com.walcker.movies.utils.CoroutineMainDispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class MoviesListViewModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(
        moviesRepository: MoviesRepository
    ): MoviesListViewModel = MoviesListViewModel(moviesRepository)

    @Test
    fun `GIVEN a successful list fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        val viewModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val uiState = viewModel.uiState.first()
        assertTrue(uiState is MoviesListViewModel.MoviesListUiState.Loading)
    }

    @Test
    fun `GIVEN a successful list fetch WHEN init completes THEN uiState should be Success`() = runTest(dispatcher) {
        val viewModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val uiState = viewModel.uiState.filter { it !is MoviesListViewModel.MoviesListUiState.Loading }.first()
        assertTrue(uiState is MoviesListViewModel.MoviesListUiState.Success)
        val movies = uiState.movies
        assertTrue(movies.isNotEmpty())
        assertTrue(movies.first().movies.first().title.isNotBlank())
    }

    @Test
    fun `GIVEN next page load WHEN loadNextPage is called THEN movies list accumulates`() = runTest(dispatcher) {
        val viewModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        // First Call
        val initialSuccess = viewModel.uiState.filter { it is MoviesListViewModel.MoviesListUiState.Success }
            .first() as MoviesListViewModel.MoviesListUiState.Success

        // Paging
        val popular = initialSuccess.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
        val initialCount = popular.movies.size

        viewModel.loadNextPage(MovieSection.SectionType.POPULAR)

        val nextSuccess = viewModel.uiState.filter { state ->
            state is MoviesListViewModel.MoviesListUiState.Success &&
                    state.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
                        .movies.size > initialCount
        }.first() as MoviesListViewModel.MoviesListUiState.Success

        val newPopular = nextSuccess.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
        assertTrue(newPopular.movies.size > initialCount)
    }

    @Test
    fun `GIVEN a failed movies list fetch WHEN init THEN uiState should be Error`() = runTest(dispatcher) {
        // Given
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val moviesRepository = FakeMoviesRepository.createFailureRepository(RuntimeException("Repository error"))
        val viewModel = createViewModel(moviesRepository = moviesRepository)

        // Then
        val uiState = viewModel.uiState.filter { it !is MoviesListViewModel.MoviesListUiState.Loading }.first()
        assertTrue(uiState is MoviesListViewModel.MoviesListUiState.Error)
        assertEquals(errorMessage, uiState.message)
    }
}
