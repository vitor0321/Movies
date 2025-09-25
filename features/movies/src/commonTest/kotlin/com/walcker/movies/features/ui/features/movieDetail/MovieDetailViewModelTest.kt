
package com.walcker.movies.features.ui.features.movieDetail

import com.walcker.movies.features.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.features.movies.features.ui.features.movieDetail.MovieDetailUiState
import com.walcker.movies.features.movies.features.ui.features.movieDetail.MovieDetailViewModel
import com.walcker.movies.mockData.domain.movieTestData1
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
internal class MovieDetailViewModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(moviesRepository: MoviesRepository): MovieDetailViewModel =
        MovieDetailViewModel(
            movieId = 1,
            moviesRepository = moviesRepository,
        )

    @Test
    fun `GIVEN a successful movie detail fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val viewModel = createViewModel(moviesRepository = moviesRepository)
        // Then
        val uiState = viewModel.uiState.first()

        println("uiState durante o teste: $uiState")

        assertTrue(uiState is MovieDetailUiState.Loading)
    }

    @Test
    fun `GIVEN a successful movie detail fetch WHEN init THEN uiState should be Success`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val viewModel = createViewModel(moviesRepository = moviesRepository)
        // Then
        val uiState = viewModel.uiState.filter { it !is MovieDetailUiState.Loading }.first()

        println("uiState durante o teste: $uiState")

        assertTrue(uiState is MovieDetailUiState.Success)
        assertEquals(movieTestData1, uiState.movie)
    }

    @Test
    fun `GIVEN a failed movie detail fetch WHEN init THEN uiState should be Error`() = runTest(dispatcher) {
        // Given
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val moviesRepository = FakeMoviesRepository.createFailureRepository(RuntimeException("Repository error"))
        val viewModel = createViewModel(moviesRepository = moviesRepository)

        // Then
        val uiState = viewModel.uiState.filter { it !is MovieDetailUiState.Loading }.first()
        assertTrue(uiState is MovieDetailUiState.Error)
        assertEquals(errorMessage, uiState.message)
    }
}