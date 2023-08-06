package com.example.impl.ui

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.api.ui.FeatureApi

class FeatureImpl : FeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(route) {
            FeatureScreen {
                navHostController.navigate("user-detail?username=$it")
            }
        }
    }
}
