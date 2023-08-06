package com.example.api.repository

import com.example.api.data.UserDetails
import com.example.api.data.UsersApi
import kotlinx.coroutines.flow.Flow

interface FeatureRepository {
    suspend fun searchUsers(query: String, page: Int): UsersApi

    suspend fun userDetails(username: String): Flow<UserDetails>
}
