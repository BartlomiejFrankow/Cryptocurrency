package com.example.domain.useCase.getCoin

import com.example.domain.common.RequestResult
import com.example.domain.model.CoinDetail
import com.example.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke(coinId: String): RequestResult<CoinDetail> {
        return repository.getCoinById(coinId)
    }
}
