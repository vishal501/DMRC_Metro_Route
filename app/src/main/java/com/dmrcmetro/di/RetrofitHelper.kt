package com.dmrcmetro.di

import com.dmrcmetro.api.ApiInterface
import com.dmrcmetro.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Provides
    @Singleton
    fun provideRetrofitInstance(BASE_URL: String) : ApiInterface =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

}