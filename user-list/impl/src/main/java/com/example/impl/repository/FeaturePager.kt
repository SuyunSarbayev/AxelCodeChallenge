package com.example.impl.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api.data.User
import com.example.api.repository.FeatureRepository

/**
 * Pager class which contains search query and repository
 * contains logic to determine if next page must be retrieved once user scrolls down and
 * load more data, if we locate that items size is less that page size we can be sure
 * that all data is loaded, in my opinion it is better for response to have variable like hasNextPage
 */
class FeaturePager(
    private val searchQuery: String,
    private val featureRepository: FeatureRepository,
) : PagingSource<Int, User>() {

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageNumber = params.key ?: 1
        return try {
            val response = featureRepository.searchUsers(searchQuery, pageNumber)

            LoadResult.Page(
                data = response.items,
                prevKey = null,
                nextKey = if (response.items.size < FeaturePagerRepository.USERS_PAGE_SIZE) null else pageNumber + 1,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
