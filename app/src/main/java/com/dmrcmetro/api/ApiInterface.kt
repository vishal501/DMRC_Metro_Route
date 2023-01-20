package com.dmrcmetro.api

import com.dmrcmetro.models.MetroModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("route-get")
    suspend fun getPosts(@Query("from") from: String?, @Query("to") to: String?): Response<MetroModel>
}