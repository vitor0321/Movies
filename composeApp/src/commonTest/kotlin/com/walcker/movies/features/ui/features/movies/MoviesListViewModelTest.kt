package com.walcker.movies.features.ui.features.movies

import com.walcker.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.mockData.domain.movieSectionTestData
import com.walcker.movies.mockFakes.FakeMoviesRepository
import com.walcker.movies.utils.CoroutineMainDispatcherTestRule
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class MoviesListViewModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(moviesRepository: MoviesRepository): MoviesListViewModel =
        MoviesListViewModel(
            moviesRepository = moviesRepository,
            dispatcher = dispatcher,
        )

    @Test
    fun `GIVEN a successful movie sections fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val viewModel = createViewModel(moviesRepository)

        // Then
        val uiState = viewModel.uiState.first()
        assertTrue(uiState is MoviesListViewModel.MoviesListUiState.Loading)
    }

    @Test
    fun `GIVEN a successful movie sections fetch WHEN init THEN uiState should be Success`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val viewModel = createViewModel(moviesRepository)

        // Then
        val uiState = viewModel.uiState.filter { it !is MoviesListViewModel.MoviesListUiState.Loading }.first()
        assertTrue(uiState is MoviesListViewModel.MoviesListUiState.Success)
        assertEquals(movieSectionTestData, uiState.movies)
    }

    @Test
    fun `GIVEN a failed movie sections fetch WHEN init THEN uiState should be Error`() = runTest(dispatcher) {
        // Given
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val moviesRepository = FakeMoviesRepository.createFailureRepository(RuntimeException("Repository error"))
        val viewModel = createViewModel(moviesRepository)

        // Then
        val uiState = viewModel.uiState.filter { it !is MoviesListViewModel.MoviesListUiState.Loading }.first()
        assertTrue(uiState is MoviesListViewModel.MoviesListUiState.Error)
        assertEquals(errorMessage, uiState.message)
    }
}