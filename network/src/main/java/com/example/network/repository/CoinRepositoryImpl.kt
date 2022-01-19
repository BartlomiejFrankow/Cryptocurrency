package com.example.network.repository

import com.example.domain.common.RequestResult
import com.example.domain.model.Coin
import com.example.domain.model.CoinDetail
import com.example.domain.repository.CoinRepository
import com.example.network.remote.CoinPaprikaApi
import com.example.network.remote.dto.toCoin
import com.example.network.remote.dto.toCoinDetail
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(private val api: CoinPaprikaApi) : CoinRepository {

    override suspend fun getCoins(): RequestResult<List<Coin>> {
        return try {
            RequestResult.Success(api.getCoins().map { it.toCoin() })
        } catch (e: HttpException) {
            RequestResult.HttpException
        } catch (e: IOException) {
            RequestResult.IOException
        }
    }

    override suspend fun getCoinById(coinId: String): RequestResult<CoinDetail> {
        return try {
            RequestResult.Success(api.getCoinById(coinId).toCoinDetail())
        } catch (e: HttpException) {
            RequestResult.HttpException
        } catch (e: IOException) {
            RequestResult.IOException
        }
    }
}
