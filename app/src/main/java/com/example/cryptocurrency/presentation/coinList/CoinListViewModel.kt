package com.example.cryptocurrency.presentation.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.R
import com.example.cryptocurrency.common.RequestResult.*
import com.example.cryptocurrency.domain.useCase.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        viewModelScope.launch {
            getCoins()
        }
    }

    private suspend fun getCoins() {
        _state.value = when (val response = getCoinsUseCase()) {
            HttpException, IOException -> CoinListState(error = R.string.error_unexpected)
            Loading -> CoinListState(isLoading = true)
            is Success -> CoinListState(coins = response.body)
        }
    }
}
