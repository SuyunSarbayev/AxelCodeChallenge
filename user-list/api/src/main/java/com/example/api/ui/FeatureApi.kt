package com.example.api.ui

import com.example.features_api.FeaturesApi

/**
 * Feature api for user list page, contains route for navigation component
 */
interface FeatureApi : FeaturesApi {
    override val route: String
        get() = "feature"
}
