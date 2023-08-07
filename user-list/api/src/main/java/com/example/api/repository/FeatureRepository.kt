package com.example.api.repository

import com.example.api.data.UserDetails
import com.example.api.data.UsersApi
import kotlinx.coroutines.flow.Flow

/**
 * Repository for user list containing searchUsers and user detail request declarations
 */
interface FeatureRepository {
    suspend fun searchUsers(query: String, page: Int): UsersApi

    suspend fun userDetails(username: String): Flow<UserDetails>
}
