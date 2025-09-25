package com.walcker.movies.handle

import com.walcker.movies.features.movies.handle.RetryStrategy
import com.walcker.movies.features.movies.handle.withRetry
import com.walcker.movies.utils.CoroutineMainDispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
internal class RetryTest : CoroutineMainDispatcherTestRule() {

    @Test
    fun `given successful action when withRetry is called then should return result without retry`() = runTest(dispatcher) {
        // Given
        val expectedResult = "Success"
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            expectedResult
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            action = action
        )

        // Then
        assertEquals(expectedResult, result)
        assertEquals(1, callCount)
    }

    @Test
    fun `given action that fails once then succeeds when withRetry is called then should retry and return result`() = runTest(dispatcher) {
        // Given
        val expectedResult = "Success after retry"
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            if (callCount == 1) {
                throw RuntimeException("First attempt failed")
            }
            expectedResult
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals(expectedResult, result)
        assertEquals(2, callCount)
    }

    @Test
    fun `given action that fails twice then succeeds when withRetry is called then should retry twice and return result`() = runTest(dispatcher) {
        // Given
        val expectedResult = "Success after 2 retries"
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            if (callCount <= 2) {
                throw RuntimeException("Attempt $callCount failed")
            }
            expectedResult
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            maxAttempts = 4,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals(expectedResult, result)
        assertEquals(3, callCount)
    }

    @Test
    fun `given action that always fails when withRetry is called then should retry max attempts and throw exception`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            throw RuntimeException("Always fails")
        }

        // When & Then
        assertFailsWith<RuntimeException> {
            withRetry(
                dispatcher = dispatcher,
                maxAttempts = 2,
                action = action
            )
        }

        testScheduler.advanceUntilIdle()
        assertEquals(3, callCount)
    }

    @Test
    fun `given exponential retry strategy when withRetry is called then should use correct delays`() = runTest(dispatcher) {
        // Given
        val strategy = RetryStrategy.Exponential(ratio = 2.0)
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            if (callCount <= 2) {
                throw RuntimeException("Fail $callCount")
            }
            "Success"
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            strategy = strategy,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals("Success", result)
        assertEquals(3, callCount)
    }

    @Test
    fun `given exponential strategy with custom ratio when withRetry is called then should calculate correct delays`() = runTest(dispatcher) {
        // Given
        val strategy = RetryStrategy.Exponential(ratio = 3.0)

        // When & Then
        assertEquals(0, strategy.getDelay(0))
        assertEquals(3000, strategy.getDelay(1))
        assertEquals(9000, strategy.getDelay(2))
        assertEquals(27000, strategy.getDelay(3))
    }

    @Test
    fun `given exponential strategy with default ratio when withRetry is called then should calculate correct delays`() = runTest(dispatcher) {
        // Given
        val strategy = RetryStrategy.Exponential()

        // When & Then
        assertEquals(0, strategy.getDelay(0))
        assertEquals(2000, strategy.getDelay(1))
        assertEquals(4000, strategy.getDelay(2))
        assertEquals(8000, strategy.getDelay(3))
    }

    @Test
    fun `given custom shouldRetry predicate when withRetry is called then should only retry on specific exceptions`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            throw IllegalArgumentException("Should not retry")
        }

        // When & Then
        assertFailsWith<IllegalArgumentException> {
            withRetry(
                dispatcher = dispatcher,
                maxAttempts = 0,
                shouldRetry = { it is RuntimeException },
                action = action
            )
        }
        assertEquals(1, callCount)
    }

    @Test
    fun `given custom shouldRetry predicate that allows retry when withRetry is called then should retry on allowed exceptions`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            "Success"
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            shouldRetry = { it is IllegalArgumentException },
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals("Success", result)
        assertEquals(1, callCount)
    }

    @Test
    fun `given maxAttempts of 0 when withRetry is called then should not retry on failure`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            throw RuntimeException("Always fails")
        }

        // When & Then
        assertFailsWith<RuntimeException> {
            withRetry(
                dispatcher = dispatcher,
                maxAttempts = 0,
                action = action
            )
        }

        assertEquals(1, callCount)
    }

    @Test
    fun `given maxAttempts of 4 when withRetry is called then should retry up to 5 times`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            throw RuntimeException("Always fails")
        }

        // When & Then
        assertFailsWith<RuntimeException> {
            withRetry(
                dispatcher = dispatcher,
                maxAttempts = 4,
                action = action
            )
        }

        testScheduler.advanceUntilIdle()
        assertEquals(5, callCount)
    }

    @Test
    fun `given action that throws different exceptions when withRetry is called then should handle correctly`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            when (callCount) {
                1 -> throw RuntimeException("First runtime exception")
                2 -> throw RuntimeException("Second runtime exception")
                else -> "Success"
            }
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals("Success", result)
        assertEquals(3, callCount)
    }

    @Test
    fun `given action that returns different types when withRetry is called then should maintain type safety`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> Int = {
            callCount++
            if (callCount == 1) {
                throw RuntimeException("First attempt failed")
            }
            42
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals(42, result)
        assertEquals(2, callCount)
    }

    @Test
    fun `given action that returns complex object when withRetry is called then should return correct object`() = runTest(dispatcher) {
        // Given
        data class TestResult(val id: Int, val message: String)
        val expectedResult = TestResult(1, "Test successful")
        var callCount = 0
        val action: suspend () -> TestResult = {
            callCount++
            if (callCount == 1) {
                throw RuntimeException("First attempt failed")
            }
            expectedResult
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals(expectedResult, result)
        assertEquals(2, callCount)
    }

    @Test
    fun `given action that succeeds immediately when withRetry is called then should not apply any delay`() = runTest(dispatcher) {
        // Given
        val action: suspend () -> String = { "Immediate success" }
        val startTime = testScheduler.currentTime

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            action = action
        )

        // Then
        assertEquals("Immediate success", result)
        assertEquals(startTime, testScheduler.currentTime) // No time should have passed
    }

    @Test
    fun `given action that fails with non-retryable exception when withRetry is called then should fail immediately`() = runTest(dispatcher) {
        // Given
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            throw IllegalStateException("Non-retryable exception")
        }

        // When & Then
        assertFailsWith<IllegalStateException> {
            withRetry(
                dispatcher = dispatcher,
                maxAttempts = 0,
                shouldRetry = { it is RuntimeException },
                action = action
            )
        }

        assertEquals(1, callCount)
    }

    @Test
    fun `given retry strategy with zero delay when withRetry is called then should retry immediately`() = runTest(dispatcher) {
        // Given
        val strategy = RetryStrategy.Exponential(ratio = 0.0)
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            if (callCount <= 2) {
                throw RuntimeException("Fail $callCount")
            }
            "Success"
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            strategy = strategy,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals("Success", result)
        assertEquals(3, callCount)
    }

    @Test
    fun `given multiple retry attempts when withRetry is called then should respect exponential backoff timing`() = runTest(dispatcher) {
        // Given
        val strategy = RetryStrategy.Exponential(ratio = 2.0)
        var callCount = 0
        val action: suspend () -> String = {
            callCount++
            if (callCount <= 3) {
                throw RuntimeException("Fail $callCount")
            }
            "Success"
        }

        // When
        val result = withRetry(
            dispatcher = dispatcher,
            strategy = strategy,
            action = action
        )

        // Then
        testScheduler.advanceUntilIdle()
        assertEquals("Success", result)
        assertEquals(4, callCount)

        // Verify that some time has passed due to delays
        assertTrue(testScheduler.currentTime > 0)
    }
}