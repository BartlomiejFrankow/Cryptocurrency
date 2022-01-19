package com.example.cryptocurrency.domain.useCase.getCoin

import com.example.cryptocurrency.R
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.toCoinDetail
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading<CoinDetail>())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetail>(R.string.error_unexpected))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetail>(R.string.error_server_connection))
        }
    }
}
