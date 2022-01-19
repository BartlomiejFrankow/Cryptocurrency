package com.example.domain.repository

import com.example.domain.common.RequestResult
import com.example.domain.model.Coin
import com.example.domain.model.CoinDetail

interface CoinRepository {

    suspend fun getCoins(): RequestResult<List<Coin>>

    suspend fun getCoinById(coinId: String): RequestResult<CoinDetail>
}
