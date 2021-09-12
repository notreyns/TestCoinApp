package com.example.rei.data.rest.client

import com.example.rei.data.rest.api.HomeApi

interface RestClient {

    fun homeApi() : HomeApi
}