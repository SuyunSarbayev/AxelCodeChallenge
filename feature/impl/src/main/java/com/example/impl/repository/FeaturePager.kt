package com.example.impl.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.api.data.User
import com.example.api.repository.FeatureRepository

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
