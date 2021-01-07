package com.akshay.movieapp.di

import com.akshay.movieapp.BuildConfig
import com.akshay.movieapp.data.NetworkService
import com.akshay.movieapp.data.Networking
import com.akshay.movieapp.data.Networking.API_KEY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class AppModule(){

    @Provides
    @Singleton
    fun provideNetworkService(): NetworkService =
        Networking.create(
            "a117e3abfbe92beb8e4ff80ace3adfc5",
           Networking.BASE_URL
        )


}