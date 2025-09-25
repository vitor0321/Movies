package com.walcker.movies.features.movies.features.ui.features.movieDetail.components

import VideoPlayer
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ModalBottomSheetDetail(
    url: String,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
    ) {
        Column(
            modifier = Modifier
                .heightIn(max = 250.dp)
                .fillMaxWidth()
        ) {
            VideoPlayer(
                modifier = Modifier.fillMaxWidth(),
                url = url,
                showControls = true,
                autoPlay = true,
            )
        }
    }
}