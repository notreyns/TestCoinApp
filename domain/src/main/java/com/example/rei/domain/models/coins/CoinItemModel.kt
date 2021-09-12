package com.example.rei.domain.models.coins

import java.io.Serializable

data class CoinItemModel(
    val icon: String?,
    val name: String?,
    val rank: Int?,
    val price: String?,
    val website: String?,
    val symbol: String?
) : Serializable