package com.example.impl.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.api.data.User
import com.example.api.repository.FeatureRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Pager repository which holds constant declaration and Pager with PagingConfig
 */
class FeaturePagerRepository @Inject constructor(
    private val featureRepository: FeatureRepository,
) {
    private var pagingConfig: PagingConfig

    init {
        pagingConfig = PagingConfig(pageSize = USERS_PAGE_SIZE)
    }

    fun initiateSearchUsers(searchQuery: String): Flow<PagingData<User>> {
        return Pager(pagingConfig) {
            FeaturePager(searchQuery, featureRepository)
        }.flow
    }

    companion object {
        const val USERS_PAGE_SIZE = 20
    }
}
