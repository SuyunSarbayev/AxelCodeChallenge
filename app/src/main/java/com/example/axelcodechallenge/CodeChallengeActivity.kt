package com.example.axelcodechallenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.impl.domain.UserDetailFeatureImpl
import com.example.impl.ui.FeatureImpl
import com.example.navigation.NavigationProvider
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        NavigationProvider.provideNavigations(
            feature = FeatureImpl(),
            userDetails = UserDetailFeatureImpl(),
        )

        setContent {
            RootView()
        }
    }
}
