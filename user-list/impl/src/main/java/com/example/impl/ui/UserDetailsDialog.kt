package com.example.impl.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.api.data.User
import com.example.impl.R
import com.example.ui.dimens.Dimens

@Composable
fun ModalBottomSheet(
    user: User,
    openDialog: MutableState<Boolean>,
    navigateUserDetailScreen: (username: String) -> Unit,
) {
    if (openDialog.value) {
        Dialog(
            onDismissRequest = {
                openDialog.value = false
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
            ),
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
            ) {
                Column {
                    DialogImage(user)
                    Spacer(modifier = Modifier.height(Dimens.viewVerticalPadding))
                    DialogMessage(user.login)
                    Spacer(modifier = Modifier.height(Dimens.viewVerticalPadding))
                    DialogActionButtons(user, openDialog, navigateUserDetailScreen)
                }
            }
        }
    }
}

@Composable
fun DialogImage(user: User) {
    androidx.compose.foundation.Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatarUrl)
                .allowHardware(false)
                .build(),
            error = ColorPainter(Color.LightGray),
            onSuccess = {},
            onError = {},
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .clip(MaterialTheme.shapes.medium)
            .fillMaxWidth(),
    )
}

@Composable
fun DialogMessage(username: String) {
    Column {
        Text(
            modifier = Modifier.padding(
                start = Dimens.contentPagePadding,
                end = Dimens.contentPagePadding,
            ),
            color = MaterialTheme.colorScheme.onSurface,
            text = stringResource(id = R.string.users_list_dialog_title, username),
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun DialogActionButtons(
    user: User,
    openDialog: MutableState<Boolean>,
    navigateUserDetailScreen: (username: String) -> Unit,
) {
    Row(
        modifier = Modifier.padding(
            bottom = Dimens.contentPagePadding,
            start = Dimens.contentPagePadding,
            end = Dimens.contentPagePadding,
        ),
    ) {
        Button(
            modifier = Modifier.padding(end = Dimens.viewHorizontalPadding),
            onClick = { openDialog.value = false },
        ) {
            Text(
                color = Color.White,
                text = stringResource(id = R.string.users_list_dialog_close_detail),
            )
        }
        Button(
            onClick = {
                openDialog.value = false
                navigateUserDetailScreen(user.login)
            },
        ) {
            Text(
                color = Color.White,
                text = stringResource(id = R.string.users_list_dialog_open_detail),
            )
        }
    }
}
