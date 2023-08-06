package com.example.navigation

import com.example.api.UserDetailFeatureApi
import com.example.api.ui.FeatureApi

object NavigationProvider {
    private lateinit var feature: FeatureApi

    private lateinit var userDetails: UserDetailFeatureApi

    fun provideNavigations(
        feature: FeatureApi,
        userDetails: UserDetailFeatureApi,
    ) {
        NavigationProvider.feature = feature
        NavigationProvider.userDetails = userDetails
    }

    fun feature(): FeatureApi = feature

    fun userDetails(): UserDetailFeatureApi = userDetails
}
