package com.example.rei.data.rest.client

import com.example.rei.data.BuildConfig
import com.example.rei.data.rest.api.HomeApi
import com.example.rei.data.rest.callAdapter.ResultCallAdapterFactory
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RestClientImpl @Inject constructor() : RestClient {

    private val retrofit: Retrofit

    companion object {
        private const val TIME_OUT_IN_SECOND: Long = 60
    }

    private val httpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(TIME_OUT_IN_SECOND, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT_IN_SECOND, TimeUnit.SECONDS)

    init {
        val gson = GsonBuilder().create()

        this.retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(ResultCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(httpClient.build())
            .build()
    }

    override fun homeApi(): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }


}