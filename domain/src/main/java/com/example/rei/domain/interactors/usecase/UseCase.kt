package com.example.rei.domain.interactors.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import com.example.rei.domain.interactors.result.Result

abstract class UseCase<T, Params> {

    abstract suspend fun doOnBackground(params: Params?): Result<T>

    fun execute(
        scope: CoroutineScope,
        params: Params? = null,
        onResult: (Result<T>) -> Unit
    ) {
        scope.launch {
            val deferred = async(Dispatchers.IO) {
                doOnBackground(params)
            }
            onResult(deferred.await())
        }
    }
}