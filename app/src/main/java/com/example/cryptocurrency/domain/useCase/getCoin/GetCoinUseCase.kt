package com.example.cryptocurrency.domain.useCase.getCoin

import com.example.cryptocurrency.common.RequestResult
import com.example.cryptocurrency.domain.model.CoinDetail
import com.example.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke(coinId: String): RequestResult<CoinDetail> {
        return repository.getCoinById(coinId)
    }
}
