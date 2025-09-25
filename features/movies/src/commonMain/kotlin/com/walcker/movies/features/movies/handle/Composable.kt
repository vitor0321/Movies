package com.walcker.movies.features.movies.handle

import androidx.compose.foundation.lazy.LazyListState
import kotlin.math.abs

internal fun getCenterItemIndex(listState: LazyListState): Int {
    val layoutInfo = listState.layoutInfo
    if (layoutInfo.visibleItemsInfo.isEmpty()) return 0
    val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
    return layoutInfo.visibleItemsInfo.minByOrNull {
        val itemCenter = it.offset + it.size / 2
        abs(itemCenter - viewportCenter)
    }?.index ?: 0
}