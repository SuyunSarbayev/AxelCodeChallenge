package com.example.api.ui

import com.example.features_api.FeaturesApi

interface FeatureApi : FeaturesApi {
    override val route: String
        get() = "feature"
}
