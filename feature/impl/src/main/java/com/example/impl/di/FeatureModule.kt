package com.example.impl.di

import com.example.api.api.FeatureApiService
import com.example.api.repository.FeatureRepository
import com.example.impl.repository.FeatureRepositoryImpl
import com.example.network.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
interface FeatureModule {

    @Binds
    fun bindsFeatureRepository(featureRepositoryImpl: FeatureRepositoryImpl): FeatureRepository

    companion object {
        @Provides
        @Singleton
        fun providesFeatureServiceApi(retrofit: Retrofit): FeatureApiService {
            return retrofit.create(FeatureApiService::class.java)
        }
    }
}
