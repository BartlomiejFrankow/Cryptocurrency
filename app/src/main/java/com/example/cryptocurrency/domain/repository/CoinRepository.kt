package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.common.RequestResult
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail

interface CoinRepository {

    suspend fun getCoins(): RequestResult<List<Coin>>

    suspend fun getCoinById(coinId: String): RequestResult<CoinDetail>
}
