package com.example.rei.domain.interactors.usecase.coins

import com.example.rei.domain.interactors.usecase.UseCase
import com.example.rei.domain.models.coins.CoinsModel
import com.example.rei.domain.repository.IHomeRepository
import com.example.rei.domain.interactors.result.Result
import javax.inject.Inject

class GetConsUseCase  @Inject constructor(
    private val iHomeRepository: IHomeRepository
) : UseCase<CoinsModel, Void>(){

    override suspend fun doOnBackground(params: Void?): Result<CoinsModel> {
        return iHomeRepository.getCoins()
    }
}