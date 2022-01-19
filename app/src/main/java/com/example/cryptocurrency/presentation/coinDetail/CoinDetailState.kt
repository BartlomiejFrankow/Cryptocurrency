package com.example.cryptocurrency.presentation.coinDetail

import com.example.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail? = null,
    val error: Int? = null
)
