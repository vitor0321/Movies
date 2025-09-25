package com.walcker.movies.core

import kotlin.test.Test
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class PlatformTest {
    @Test
    fun platformImpl_hasLanguageAndAccessTokenProperties() {
        val platform = platformImpl()
        assertTrue(platform.languageSystem.isNotBlank(), "languageSystem should not be blank")
        assertNotNull(platform.accessToken)
    }
}