package com.example.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.api.data.User
import com.example.ui.dimens.Dimens

@Composable
fun UserItem(item: User) {
    Row(
        Modifier.background(color = MaterialTheme.colorScheme.onSecondary).fillMaxWidth(),
    ) {
        Image(item)
        Username(item)
    }
}

@Composable
fun Image(item: User) {
    androidx.compose.foundation.Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(item.avatarUrl)
                .allowHardware(false)
                .build(),
            error = ColorPainter(Color.LightGray),
            onSuccess = {},
            onError = {},
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(100.dp)
            .aspectRatio(Dimens.usersAspectRatio)
            .clip(MaterialTheme.shapes.medium),
    )
}

@Composable
fun Username(item: User) {
    Text(
        modifier = Modifier.padding(
            top = Dimens.viewVerticalPadding,
            start = Dimens.contentPagePadding,
            end = Dimens.contentPagePadding,
        ),
        color = MaterialTheme.colorScheme.onSurface,
        fontWeight = FontWeight.Bold,
        fontSize = Dimens.textSizeLarge,
        text = item.login,
    )
}
