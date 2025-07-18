package com.walcker.movies.handle

import androidx.compose.foundation.lazy.LazyListState

internal fun getCenterItemIndex(listState: LazyListState): Int {
    val layoutInfo = listState.layoutInfo
    if (layoutInfo.visibleItemsInfo.isEmpty()) return 0
    val viewportCenter = (layoutInfo.viewportStartOffset + layoutInfo.viewportEndOffset) / 2
    return layoutInfo.visibleItemsInfo.minByOrNull {
        val itemCenter = it.offset + it.size / 2
        kotlin.math.abs(itemCenter - viewportCenter)
    }?.index ?: 0
}