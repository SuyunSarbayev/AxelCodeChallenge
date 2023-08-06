package com.example.impl.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.impl.R
import com.example.impl.domain.FeatureViewModel
import com.example.ui.dimens.Dimens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FeatureScreen(
    navigateUserDetailScreen: (username: String) -> Unit,
) {
    val viewModel = hiltViewModel<FeatureViewModel>()

    val searchQuery by viewModel.search.collectAsState()

    val users = viewModel.usersPaging.collectAsLazyPagingItems()

    val isRefreshing = users.loadState.refresh is LoadState.Loading

    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, { users.refresh() })

    Box(
        Modifier
            .pullRefresh(pullRefreshState)
            .fillMaxWidth()
            .fillMaxHeight(),
    ) {
        Column {
            TopSearchBar(
                query = searchQuery,
                onQueryChanged = { viewModel.setSearchQuery(it) },
            )
            users.let {
                when {
                    it.loadState.refresh is LoadState.Loading -> {
                        UsersShimmer()
                    }

                    it.itemCount == 0 -> {
                        UsersEmpty { users.refresh() }
                    }

                    else -> {
                        UsersList(users, navigateUserDetailScreen)
                    }
                }
            }
        }
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter),
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TopSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
) {
    SearchBar(
        modifier = Modifier
            .padding(
                bottom = Dimens.viewHorizontalPadding,
                end = Dimens.viewHorizontalPadding,
                start = Dimens.viewHorizontalPadding,
            )
            .fillMaxWidth(),
        query = query,
        onQueryChange = { onQueryChanged(it) },
        onSearch = { },
        active = false,
        onActiveChange = { },
        placeholder = {
            Text(
                stringResource(R.string.users_list_search_query_hint),
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        leadingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = null,
            )
        },
    ) { }
}
