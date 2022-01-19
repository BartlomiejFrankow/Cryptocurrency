package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.common.RequestResult
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.remote.dto.toCoin
import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
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
