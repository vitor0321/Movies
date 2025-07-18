
package com.walcker.movies.features.ui.features.movieDetail

import com.walcker.movies.features.domain.repository.MoviesRepository
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

    @Test
    fun `GIVEN fetchTrailerUrl WHEN repository returns success THEN trailerUrl should be updated`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val viewModel = createViewModel(moviesRepository)

        // When
        viewModel.onEvent(MovieDetailInternalRoute.OnFetchTrailerUrl)

        // Then
        val trailerUrl = viewModel.trailerUrl.filter { it != null }.first()
        assertEquals("https://www.youtube.com/watch?v=1234567890", trailerUrl)
    }

    @Test
    fun `GIVEN fetchTrailerUrl WHEN repository returns failure THEN trailerUrl should be null`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createFailureRepository()
        val viewModel = createViewModel(moviesRepository)

        // When
        viewModel.onEvent(MovieDetailInternalRoute.OnFetchTrailerUrl)

        // Then
        val trailerUrl = viewModel.trailerUrl.first()
        assertEquals(null, trailerUrl)
    }

    @Test
    fun `GIVEN resetTrailerUrl WHEN previously set THEN trailerUrl should be null`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val viewModel = createViewModel(moviesRepository)
        viewModel.onEvent(MovieDetailInternalRoute.OnFetchTrailerUrl)
        viewModel.trailerUrl.filter { it != null }.first()

        // When
        viewModel.onEvent(MovieDetailInternalRoute.OnResetTrailerUrl)

        // Then
        val trailerUrl = viewModel.trailerUrl.first()
        assertEquals(null, trailerUrl)
    }
}