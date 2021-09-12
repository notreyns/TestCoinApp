package com.example.rei.data.mappers.coins

import com.example.rei.data.entity.CoinsEntity
import com.example.rei.data.mappers.SimpleEntityDataMapper
import com.example.rei.domain.models.coins.CoinItemModel
import com.example.rei.domain.models.coins.CoinsModel
import javax.inject.Inject

class CoinDataMapper @Inject constructor() :
    SimpleEntityDataMapper<CoinsModel, CoinsEntity>() {

    override fun transform(entity: CoinsEntity): CoinsModel {
        return CoinsModel(
            getCoinList(entity)
        )
    }

    private fun getCoinList(entity: CoinsEntity): Collection<CoinItemModel> {
        val collection = mutableListOf<CoinItemModel>()

        entity.coins?.forEach { itemEntity ->
            collection.add(
                CoinItemModel(
                    icon = itemEntity.icon,
                    name = itemEntity.name ?: "N/A",
                    rank = itemEntity.rank,
                    price = "%.2f".format(itemEntity.price ?: 0.0),
                    website = itemEntity.website ?: "N/A",
                    symbol = itemEntity.symbol ?: "N/A"
                )
            )
        }

        return collection
    }
}