package com.example.rei.data.entity

import com.google.gson.annotations.SerializedName

data class CoinItemEntity(
    @SerializedName("icon")
    val icon: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("rank")
    val rank: Int?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("websiteUrl")
    val website: String?,
    @SerializedName("symbol")
    val symbol: String?
)
