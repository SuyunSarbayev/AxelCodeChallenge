package com.example.impl.domain

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.api.UserDetailFeatureApi
import com.example.impl.ui.UserDetailScreen

/**
 * Feature module entry point declaration, registerGraph implementation from extension
 * function declared in featureApi module, which add composable route with navArgument username
 * to pass login param for detail request
 */
class UserDetailFeatureImpl : UserDetailFeatureApi {
    override fun registerGraph(
        navHostController: NavHostController,
        navGraphBuilder: NavGraphBuilder,
        modifier: Modifier,
    ) {
        navGraphBuilder.composable(
            route,
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                },
            ),
        ) {
            UserDetailScreen()
        }
    }
}
