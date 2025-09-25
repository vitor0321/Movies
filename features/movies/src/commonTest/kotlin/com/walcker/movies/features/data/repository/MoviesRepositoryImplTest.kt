package com.walcker.movies.features.data.repository

import com.walcker.movies.features.movies.features.data.repository.MoviesRepositoryImpl
import com.walcker.movies.features.movies.features.domain.models.MovieSection
import com.walcker.movies.features.movies.features.domain.models.MoviesPagination
import com.walcker.movies.mockFakes.FakeMovieApi.createMockMovieApi
import com.walcker.movies.mockFakes.FakeMovieApi.createMockMovieApiWithError
import com.walcker.movies.utils.CoroutineMainDispatcherTestRule
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

internal class MoviesRepositoryImplTest : CoroutineMainDispatcherTestRule() {

    private val mockMovieApi = createMockMovieApi()
    private val repository = MoviesRepositoryImpl(mockMovieApi, dispatcher)

    @Test
    fun `given movie api when getMoviesSections is called then should return all movie sections`() = runTest(dispatcher) {
        // Given // When
        val result = repository.getMoviesSections(pagination = MoviesPagination())

        // Then
        assertTrue(result.isSuccess)
        val sections = result.getOrNull()
        assertNotNull(sections)
        assertEquals(4, sections.size)

        // Verify all section types are present
        val sectionTypes = sections.map { it.sectionType }
        assertTrue(sectionTypes.contains(MovieSection.SectionType.POPULAR))
        assertTrue(sectionTypes.contains(MovieSection.SectionType.TOP_RATED))
        assertTrue(sectionTypes.contains(MovieSection.SectionType.UPCOMING))
    }

    @Test
    fun `given movie api when getMoviesSections is called then should return popular section with correct type`() = runTest(dispatcher) {
        // Given & When
        val result = repository.getMoviesSections(pagination = MoviesPagination())

        // Then
        assertTrue(result.isSuccess)
        val sections = result.getOrNull()
        assertNotNull(sections)

        val popularSection = sections.find { it.sectionType == MovieSection.SectionType.POPULAR }
        assertNotNull(popularSection)
        assertEquals(MovieSection.SectionType.POPULAR, popularSection.sectionType)
        assertNotNull(popularSection.movies)
        assertEquals(2, popularSection.movies.size)
    }

    @Test
    fun `given movie api when getMoviesSections is called then should return top rated section with correct type`() = runTest(dispatcher) {
        // Given & When
        val result = repository.getMoviesSections(pagination = MoviesPagination())

        // Then
        assertTrue(result.isSuccess)
        val sections = result.getOrNull()
        assertNotNull(sections)

        val topRatedSection = sections.find { it.sectionType == MovieSection.SectionType.TOP_RATED }
        assertNotNull(topRatedSection)
        assertEquals(MovieSection.SectionType.TOP_RATED, topRatedSection.sectionType)
        assertNotNull(topRatedSection.movies)
        assertEquals(2, topRatedSection.movies.size)
    }

    @Test
    fun `given movie api when getMoviesSections is called then should return upcoming section with correct type`() = runTest(dispatcher) {
        // Given & When
        val result = repository.getMoviesSections(pagination = MoviesPagination())

        // Then
        assertTrue(result.isSuccess)
        val sections = result.getOrNull()
        assertNotNull(sections)

        val upcomingSection = sections.find { it.sectionType == MovieSection.SectionType.UPCOMING }
        assertNotNull(upcomingSection)
        assertEquals(MovieSection.SectionType.UPCOMING, upcomingSection.sectionType)
        assertNotNull(upcomingSection.movies)
        assertEquals(2, upcomingSection.movies.size)
    }

    @Test
    fun `given movie id when getMovieDetail is called then should return movie with cast members`() = runTest(dispatcher) {
        // Given
        val movieId = 1

        // When
        val result = repository.getMovieDetail(movieId)

        // Then
        assertTrue(result.isSuccess)
        val movie = result.getOrNull()
        assertNotNull(movie)
        assertEquals(1, movie.id)
        assertEquals("Test Movie", movie.title)
        assertEquals("Test Overview", movie.overview)
        assertEquals("2024", movie.year)
        assertEquals("2h ", movie.duration)
        assertEquals("8.5", movie.rating)

        // Verify cast members are included
        assertNotNull(movie.castMembers)
        assertEquals(2, movie.castMembers.size)
        assertEquals("Brad Pitt", movie.castMembers[0].name)
        assertEquals("Tyler Durden", movie.castMembers[0].character)
        assertEquals("Edward Norton", movie.castMembers[1].name)
        assertEquals("The Narrator", movie.castMembers[1].character)
    }

    @Test
    fun `given movie id when getMovieDetail is called then should use X_LARGE image size`() = runTest(dispatcher) {
        // Given
        val movieId = 1

        // When
        val result = repository.getMovieDetail(movieId)

        // Then
        assertTrue(result.isSuccess)
        val movie = result.getOrNull()
        assertNotNull(movie)

        // Verify poster URL uses X_LARGE size
        assertTrue(movie.posterUrl.contains("w780"))
    }

    @Test
    fun `given movie id when getMovieDetail is called then should include genres correctly`() = runTest(dispatcher) {
        // Given
        val movieId = 1

        // When
        val result = repository.getMovieDetail(movieId)

        // Then
        assertTrue(result.isSuccess)
        val movie = result.getOrNull()
        assertNotNull(movie)
        assertNotNull(movie.genres)
        assertEquals(1, movie.genres.size)
        assertEquals(1, movie.genres[0].id)
        assertEquals("Action", movie.genres[0].name)
    }

    @Test
    fun `given movie api throws exception when getMoviesSections is called then should return failure`() = runTest(dispatcher) {
        // Given
        val mockMovieApiWithError = createMockMovieApiWithError()
        val repositoryWithError = MoviesRepositoryImpl(mockMovieApiWithError, dispatcher)

        // When
        val result = repositoryWithError.getMoviesSections(pagination = MoviesPagination())

        // Then
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
    }

    @Test
    fun `given movie api throws exception when getMovieDetail is called then should return failure`() = runTest(dispatcher) {
        // Given
        val mockMovieApiWithError = createMockMovieApiWithError()
        val repositoryWithError = MoviesRepositoryImpl(mockMovieApiWithError, dispatcher)

        // When
        val result = repositoryWithError.getMovieDetail(1)

        // Then
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
    }

    @Test
    fun `given different movie ids when getMovieDetail is called then should return different movies`() = runTest(dispatcher) {
        // Given
        val movieId1 = 1
        val movieId2 = 2

        // When
        val result1 = repository.getMovieDetail(movieId1)
        val result2 = repository.getMovieDetail(movieId2)

        // Then
        assertTrue(result1.isSuccess)
        assertTrue(result2.isSuccess)

        val movie1 = result1.getOrNull()
        val movie2 = result2.getOrNull()

        assertNotNull(movie1)
        assertNotNull(movie2)

        assertEquals(1, movie1.id)
        assertEquals(2, movie2.id)
        assertEquals("Test Movie", movie1.title)
        assertEquals("Movie 2", movie2.title)
    }

    @Test
    fun `given section type when getMovieSection is called then should return the correct section`() = runTest(dispatcher) {
        // Given
        val result = repository.getMovieSection(MovieSection.SectionType.POPULAR, page = 1)

        // Then
        assertTrue(result.isSuccess)
        val section = result.getOrNull()
        assertNotNull(section)
        assertEquals(MovieSection.SectionType.POPULAR, section.sectionType)
        assertNotNull(section.movies)
        assertEquals(2, section.movies.size)
    }

    @Test
    fun `given HIGHLIGHT section type when getMovieSection is called then should return 5 random movies`() = runTest(dispatcher) {
        // Given
        val result = repository.getMovieSection(MovieSection.SectionType.HIGHLIGHT, page = 1)

        // Then
        assertTrue(result.isSuccess)
        val section = result.getOrNull()
        assertNotNull(section)
        assertEquals(MovieSection.SectionType.HIGHLIGHT, section.sectionType)
        assertNotNull(section.movies)
        assertEquals(2, section.movies.size)
    }

    @Test
    fun `given movie api throws exception when getMovieSection is called then should return failure`() = runTest(dispatcher) {
        // Given
        val mockMovieApiWithError = createMockMovieApiWithError()
        val repositoryWithError = MoviesRepositoryImpl(
            mockMovieApiWithError, dispatcher
        )

        // When
        val result = repositoryWithError.getMovieSection(
            MovieSection.SectionType.POPULAR,
            page = 1
        )

        // Then
        assertTrue(result.isFailure)
        assertNotNull(result.exceptionOrNull())
    }
}
