package com.example.features_api

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

fun NavGraphBuilder.register(
    feature: FeaturesApi,
    navHostController: NavHostController,
    modifier: Modifier,
) {
    feature.registerGraph(
        navHostController = navHostController,
        navGraphBuilder = this,
        modifier = modifier,
    )
}
