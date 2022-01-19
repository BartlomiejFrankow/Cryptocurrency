package com.example.domain.useCase.getCoins

import com.example.domain.common.RequestResult
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke(): RequestResult<List<Coin>> {
        return repository.getCoins()
    }
}
