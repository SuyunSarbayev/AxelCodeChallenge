package com.example.api

import com.example.features_api.FeaturesApi

interface UserDetailFeatureApi : FeaturesApi {
    override val route: String
        get() = "user-detail?username={username}"
}
