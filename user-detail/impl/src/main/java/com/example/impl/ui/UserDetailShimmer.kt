package com.example.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ui.animations.loadingShimmerBrush

@Composable
fun UserDetailShimmer() {
    Box {
        LazyColumn(Modifier.fillMaxHeight()) {
            item { UserBannerShimmer() }
            item { BannerSpacer() }
            item { UserAuthorNameShimmer() }
            item { UserDetailInformationShimmer() }
        }
    }
}

@Composable
fun BannerSpacer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp),
    )
}

@Composable
fun UserBannerShimmer() {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(brush = loadingShimmerBrush()),
    )
}

@Composable
fun UserAuthorNameShimmer() {
    Spacer(
        modifier = Modifier
            .width(200.dp)
            .height(40.dp)
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 2.dp)
            .background(shape = RoundedCornerShape(16.dp), brush = loadingShimmerBrush()),
    )
}

@Composable
fun UserDetailInformationShimmer() {
    Row {
        Spacer(
            modifier = Modifier
                .weight(1.0f)
                .height(45.dp)
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                .background(shape = RoundedCornerShape(16.dp), brush = loadingShimmerBrush()),
        )

        Spacer(
            modifier = Modifier
                .weight(1.0f)
                .height(45.dp)
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                .background(shape = RoundedCornerShape(16.dp), brush = loadingShimmerBrush()),
        )

        Spacer(
            modifier = Modifier
                .weight(1.0f)
                .height(45.dp)
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp)
                .background(shape = RoundedCornerShape(16.dp), brush = loadingShimmerBrush()),
        )
    }
}

@Preview
@Composable
fun UserDetailShimmerPreview() {
    UserDetailScreen()
}
