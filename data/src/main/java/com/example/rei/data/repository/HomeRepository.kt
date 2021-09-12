package com.example.rei.data.repository

import com.example.rei.data.mappers.coins.CoinDataMapper
import com.example.rei.data.rest.client.RestClient
import com.example.rei.domain.interactors.result.Result
import com.example.rei.domain.models.coins.CoinsModel
import com.example.rei.domain.repository.IHomeRepository
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val restClient: RestClient,
    private val coinsMapper: CoinDataMapper
) : IHomeRepository{

    override fun getCoins(): Result<CoinsModel> {
        return restClient.homeApi().getCoins().map {
            coinsMapper.transform(it)
        }
    }
}