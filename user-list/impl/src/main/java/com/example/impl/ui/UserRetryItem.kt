package com.example.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.impl.R

@Composable
fun UserRetryItem(retry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
    ) {
        Button(
            modifier = Modifier.width(160.dp).align(Alignment.Center),
            onClick = { retry.invoke() },
        ) {
            Text(
                color = Color.White,
                text = stringResource(id = R.string.users_list_search_reload_page),
            )
        }
    }
}
