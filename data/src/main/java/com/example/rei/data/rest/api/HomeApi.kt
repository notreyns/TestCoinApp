package com.example.rei.data.rest.api

import com.example.rei.data.entity.CoinsEntity
import com.example.rei.domain.interactors.result.Result
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {

    @GET("coins")
    fun getCoins(
        @Query("skip")
        skip: Int = 0,
        @Query("limit")
        limit: Int = 100
    ): Result<CoinsEntity>
}