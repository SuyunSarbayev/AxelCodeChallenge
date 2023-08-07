package com.example.api

import com.example.features_api.FeaturesApi

/**
 * Feature api for detail user detail page, contains route for navigation component
 * and username as a parameter to pass as argument to get detailed information
 */
interface UserDetailFeatureApi : FeaturesApi {
    override val route: String
        get() = "user-detail?username={username}"
}
