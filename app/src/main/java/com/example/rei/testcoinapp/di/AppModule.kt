package com.example.rei.testcoinapp.di

import com.example.rei.data.mappers.coins.CoinDataMapper
import com.example.rei.data.repository.HomeRepository
import com.example.rei.data.rest.client.RestClient
import com.example.rei.data.rest.client.RestClientImpl
import com.example.rei.domain.repository.IHomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRestClient(): RestClient {
        return RestClientImpl()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(
        restClient: RestClient,
        coinDataMapper: CoinDataMapper
    ): IHomeRepository {
        return HomeRepository(
            restClient = restClient,
            coinsMapper = coinDataMapper
        )
    }
}