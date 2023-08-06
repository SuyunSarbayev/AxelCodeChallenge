package com.example.impl.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ui.animations.loadingShimmerBrush
import com.example.ui.dimens.Dimens

@Composable
fun UsersShimmer() {
    LazyColumn(
        contentPadding = PaddingValues(Dimens.contentPagePadding),
        content = {
            items(10) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .aspectRatio(Dimens.usersAspectRatio)
                        .padding(all = Dimens.viewShimmerPaddingSmall),
                    verticalAlignment = Alignment.Top,
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterVertically)
                            .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                            .background(brush = loadingShimmerBrush()),
                    )

                    Column(Modifier.align(Alignment.CenterVertically).height(100.dp)) {
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.viewVerticalPadding)
                                .height(15.dp)
                                .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                                .background(brush = loadingShimmerBrush()),
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.viewVerticalPadding)
                                .height(15.dp)
                                .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                                .background(brush = loadingShimmerBrush()),
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(Dimens.viewVerticalPadding)
                                .height(15.dp)
                                .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                                .background(brush = loadingShimmerBrush()),
                        )
                    }
                }
            }
        },
    )
}

@Composable
fun UsersAppendShimmer() {
    Row(
        modifier = Modifier
            .aspectRatio(Dimens.usersAspectRatio)
            .padding(all = Dimens.viewShimmerPaddingSmall),
        verticalAlignment = Alignment.Top,
    ) {
        Spacer(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                .background(brush = loadingShimmerBrush()),
        )

        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                    .background(brush = loadingShimmerBrush()),
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                    .background(brush = loadingShimmerBrush()),
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(15.dp)
                    .clip(RoundedCornerShape(Dimens.roundedCornerImage))
                    .background(brush = loadingShimmerBrush()),
            )
        }
    }
}
