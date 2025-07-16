package com.walcker.movies.mockFakes

import androidx.lifecycle.SavedStateHandle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class MockSavedStateHandle(
    private val initialData: Map<String, Any?> = emptyMap()
) {

    private val data = mutableMapOf<String, Any?>().apply {
        putAll(initialData)
    }

    private val stateFlows = mutableMapOf<String, MutableStateFlow<Any?>>()

    fun <T> get(key: String): T? {
        @Suppress("UNCHECKED_CAST")
        return data[key] as? T
    }

    fun <T> set(key: String, value: T?) {
        data[key] = value
        stateFlows[key]?.value = value
    }

    fun <T> getStateFlow(key: String, initialValue: T): StateFlow<T> {
        val currentValue = get<T>(key) ?: initialValue

        @Suppress("UNCHECKED_CAST")
        val flow = stateFlows.getOrPut(key) {
            MutableStateFlow(currentValue)
        } as MutableStateFlow<T>

        return flow.asStateFlow()
    }

    fun contains(key: String): Boolean {
        return data.containsKey(key)
    }

    fun remove(key: String): Any? {
        stateFlows.remove(key)
        return data.remove(key)
    }

    fun keys(): Set<String> {
        return data.keys
    }
}

internal object FakeSavedStateHandle  {

    fun create(movieId: Int): MockSavedStateHandle {
        return MockSavedStateHandle(mapOf("movieId" to movieId))
    }

    fun createWithData(data: Map<String, Any?>): MockSavedStateHandle {
        return MockSavedStateHandle(data)
    }

    fun createEmpty(): MockSavedStateHandle {
        return MockSavedStateHandle()
    }
}
