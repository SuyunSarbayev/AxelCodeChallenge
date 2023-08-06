package com.example.impl.repository

import com.example.api.api.FeatureApiService
import com.example.api.data.UserDetails
import com.example.api.data.UsersApi
import com.example.api.repository.FeatureRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FeatureRepositoryImpl @Inject constructor(private val featureApiService: FeatureApiService) :
    FeatureRepository {
    override suspend fun searchUsers(query: String, page: Int): UsersApi {
        return featureApiService.searchUsers(query, page)
    }

    override suspend fun userDetails(username: String): Flow<UserDetails> {
        return flow { emit(featureApiService.userDetails(username)) }
    }
}
