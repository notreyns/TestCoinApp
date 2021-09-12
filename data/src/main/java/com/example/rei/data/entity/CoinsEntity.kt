package com.example.rei.data.entity

import com.google.gson.annotations.SerializedName

data class CoinsEntity(
    @SerializedName("coins")
    val coins: Collection<CoinItemEntity>?
)
