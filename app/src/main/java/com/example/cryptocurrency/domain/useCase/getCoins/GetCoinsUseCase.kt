package com.example.cryptocurrency.domain.useCase.getCoins

import com.example.cryptocurrency.common.RequestResult
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke(): RequestResult<List<Coin>> {
        return repository.getCoins()
    }
}
