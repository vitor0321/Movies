package com.walcker.movies.features.ui.features.movies

import com.walcker.movies.features.movies.features.ui.features.movies.MoviesListUiState
import com.walcker.movies.features.movies.features.ui.features.movies.MoviesListViewModel
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.features.movies.features.ui.features.movies.MoviesListInternalRoute
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
        assertTrue(uiState is MoviesListUiState.Loading)
    }

    @Test
    fun `GIVEN a successful list fetch WHEN init completes THEN uiState should be Success`() = runTest(dispatcher) {
        val viewModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val uiState = viewModel.uiState.filter { it !is MoviesListUiState.Loading }.first()
        assertTrue(uiState is MoviesListUiState.Success)
        val movies = uiState.movies
        assertTrue(movies.isNotEmpty())
        assertTrue(movies.first().movies.first().title.isNotBlank())
    }

    @Test
    fun `GIVEN next page load WHEN loadNextPage is called THEN movies list accumulates`() = runTest(dispatcher) {
        val viewModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        // First Call
        val initialSuccess = viewModel.uiState.filter { it is MoviesListUiState.Success }
            .first() as MoviesListUiState.Success

        // Paging
        val popular = initialSuccess.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
        val initialCount = popular.movies.size

        viewModel.onEvent(MoviesListInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))

        val nextSuccess = viewModel.uiState.filter { state ->
            state is MoviesListUiState.Success &&
                    state.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
                        .movies.size > initialCount
        }.first() as MoviesListUiState.Success

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
        val uiState = viewModel.uiState.filter { it !is MoviesListUiState.Loading }.first()
        assertTrue(uiState is MoviesListUiState.Error)
        assertEquals(errorMessage, uiState.message)
    }

    @Test
    fun `WHEN loadNextPage called THEN only that section is updated and Success state is emitted`() = runTest(dispatcher) {
        // Given
        val viewModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        viewModel.uiState.filter { it is MoviesListUiState.Success }.first()

        // When
        viewModel.onEvent(MoviesListInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))

        // Then
        val state = viewModel.uiState.filter { it is MoviesListUiState.Success }.first()
        assertTrue(state is MoviesListUiState.Success)

        val sections = state.movies
        val updatedSection = sections.first { it.sectionType == MovieSection.SectionType.POPULAR }
        assertEquals(5, updatedSection.movies.size)
    }

    @Test
    fun `WHEN loadNextPage called with error THEN emits Error state`() = runTest(dispatcher) {
        // Given
        val errorViewModel = MoviesListViewModel(FakeMoviesRepository.createFailureRepository())

        // When
        errorViewModel.onEvent(MoviesListInternalRoute.OnLoadNextPage(MovieSection.SectionType.TOP_RATED))

        // Then
        val state = errorViewModel.uiState.filter { it is MoviesListUiState.Error }.first()
        assertTrue(state is MoviesListUiState.Error)
    }
}
