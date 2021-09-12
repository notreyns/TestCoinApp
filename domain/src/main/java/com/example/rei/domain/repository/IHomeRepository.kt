package com.example.rei.domain.repository

import com.example.rei.domain.interactors.result.Result
import com.example.rei.domain.models.coins.CoinsModel

interface IHomeRepository {
    fun getCoins() : Result<CoinsModel>
}