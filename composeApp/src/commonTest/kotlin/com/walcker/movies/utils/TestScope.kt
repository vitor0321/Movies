package com.walcker.movies.utils

import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

internal fun <T : Any> testBlock(
    createSetup: () -> T,
    testBlock: suspend TestScope.(T) -> Unit
) = runTest {
    testBlock(createSetup())
}