package com.example.impl.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.api.data.User
import com.example.ui.dimens.Dimens
import kotlinx.coroutines.launch

@Composable
fun UsersList(
    usersPaging: LazyPagingItems<User>,
    navigateUserDetailScreen: (username: String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    usersPaging.let {
        LazyColumn(
            contentPadding = PaddingValues(Dimens.contentPagePadding),
        ) {
            items(usersPaging.itemCount) {
                usersPaging[it]?.let { user ->
                    val openDialog = remember { mutableStateOf(false) }
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier
                            .padding(bottom = Dimens.contentPagePadding)
                            .clickable {
                                coroutineScope.launch { openDialog.value = true }
                            },
                        elevation = 10.dp,
                    ) {
                        UserItem(user)
                    }
                    ModalBottomSheet(user, openDialog, navigateUserDetailScreen)
                }
            }
            if (it.loadState.append is LoadState.Loading) {
                item { UsersAppendShimmer() }
            }
            if (it.loadState.refresh is LoadState.Error || it.loadState.append is LoadState.Error) {
                item {
                    UserRetryItem() { it.retry() }
                }
            }
        }
    }
}
