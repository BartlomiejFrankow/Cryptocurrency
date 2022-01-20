package com.example.cryptocurrency.presentation.coinDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.R
import com.example.domain.common.Constants
import com.example.domain.common.RequestResult
import com.example.domain.common.RequestResult.*
import com.example.domain.model.CoinDetail
import com.example.domain.useCase.getCoin.GetCoinUseCase
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
        savedStateHandle.get<String>(Constants.ARG_COIN_ID)?.let { coinId ->
            viewModelScope.launch {
                getCoin(getCoinUseCase(coinId))
            }
        }
    }

    fun getCoin(results: RequestResult<CoinDetail>?) {
        results?.let {
            _state.value = when (results) {
                HttpException -> CoinDetailState(error = R.string.error_unexpected)
                IOException -> CoinDetailState(error = R.string.error_server_connection)
                Loading -> CoinDetailState(isLoading = true)
                is Success -> CoinDetailState(coin = results.body)
            }
        }
    }
}
