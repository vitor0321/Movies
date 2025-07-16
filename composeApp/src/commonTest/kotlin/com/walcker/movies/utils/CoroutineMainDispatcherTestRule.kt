package com.walcker.movies.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

@OptIn(ExperimentalCoroutinesApi::class)
internal abstract class CoroutineMainDispatcherTestRule(
    val dispatcher: TestDispatcher = StandardTestDispatcher(),
) {
    @BeforeTest
    internal fun setUp() {
        Dispatchers.setMain(dispatcher)
    }

    @AfterTest
    internal fun tearDown() {
        Dispatchers.resetMain()
    }
}