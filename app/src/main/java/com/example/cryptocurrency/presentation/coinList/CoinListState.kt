package com.example.cryptocurrency.presentation.coinList

import com.example.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: Int? = null
)
