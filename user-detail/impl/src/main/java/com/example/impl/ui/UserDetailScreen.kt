package com.example.impl.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.api.data.UserDetails
import com.example.impl.R
import com.example.impl.domain.UserDetailFeatureViewModel
import com.example.ui.state.UiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserDetailScreen() {
    val viewModel = hiltViewModel<UserDetailFeatureViewModel>()

    val userDetailState = viewModel.userDetailState.collectAsState()

    val isRefreshing = userDetailState.value is UiState.Loading

    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, { viewModel.initiateRequestUserDetail() })

    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        when (userDetailState.value) {
            is UiState.Loading -> UserDetailShimmer()
            is UiState.Success -> {
                val data = (userDetailState.value as UiState.Success).data
                LazyColumn(modifier = Modifier.fillMaxHeight()) {
                    item {
                        UserAvatarBanner(data?.avatarUrl ?: "")
                    }
                    item {
                        UserAuthorName(data?.login ?: "")
                    }
                    item {
                        UserDetailInformation(data)
                    }
                }
            }

            is UiState.Error -> {}
        }
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
        )
    }
}

@Composable
fun UserAvatarBanner(largeImageURL: String?) {
    Image(
        painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(largeImageURL)
                .allowHardware(false)
                .build(),
            error = ColorPainter(Color.LightGray),
            onSuccess = {},
            onError = {},
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    )
}

@Composable
fun UserAuthorName(name: String) {
    Text(
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 2.dp),
        fontSize = 35.sp,
        text = name,
    )
}

@Composable
fun UserDetailInformation(userDetails: UserDetails?) {
    Row {
        UserRepositories(userDetails?.publicRepos ?: 0)
        UserFollowers(userDetails?.followers ?: 0)
    }
}

@Composable
fun UserRepositories(repositories: Int) {
    Row {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
            imageVector = Icons.Default.Star,
            contentDescription = "",
        )
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .align(Alignment.CenterVertically),
            fontSize = 14.sp,
            text = stringResource(id = R.string.user_detail_repository, repositories),
        )
    }
}

@Composable
fun UserFollowers(followers: Int) {
    Row {
        Icon(
            tint = MaterialTheme.colorScheme.onSurface,
            modifier =
            Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 5.dp),
            imageVector = Icons.Default.Person,
            contentDescription = "",
        )
        Text(
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.align(Alignment.CenterVertically),
            fontSize = 14.sp,
            text = stringResource(id = R.string.user_detail_followers, followers),
        )
    }
}

@Preview
@Composable
fun UserDetailScreenPreview() {
    UserDetailScreen()
}
