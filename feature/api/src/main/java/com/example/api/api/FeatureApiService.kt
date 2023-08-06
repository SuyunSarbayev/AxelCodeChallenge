package com.example.api.api

import com.example.api.data.UserDetails
import com.example.api.data.UsersApi
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FeatureApiService {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
    ): UsersApi

    @GET("users/{username}")
    suspend fun userDetails(
        @Path("username") username: String,
    ): UserDetails
}
