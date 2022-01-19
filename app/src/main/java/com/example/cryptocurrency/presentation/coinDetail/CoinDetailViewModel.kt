package com.example.cryptocurrency.presentation.coinDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.R
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.common.RequestResult.*
import com.example.cryptocurrency.domain.useCase.getCoin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.ARG_COIN_ID)?.let {
            viewModelScope.launch {
                getCoin(it)
            }
        }
    }

    private suspend fun getCoin(coinId: String) {
        _state.value = when (val results = getCoinUseCase(coinId)) {
            HttpException, IOException -> CoinDetailState(error = R.string.error_unexpected)
            Loading -> CoinDetailState(isLoading = true)
            is Success -> CoinDetailState(coin = results.body)
        }
    }
}