package com.example.cryptocurrency.presentation.coinList

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.R
import com.example.domain.common.RequestResult
import com.example.domain.common.RequestResult.*
import com.example.domain.model.Coin
import com.example.domain.useCase.getCoins.GetCoinsUseCase
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
            getCoins(getCoinsUseCase())
        }
    }

    fun getCoins(response: RequestResult<List<Coin>>?) {
        response?.let {
            _state.value = when (response) {
                HttpException -> CoinListState(error = R.string.error_unexpected)
                IOException -> CoinListState(error = R.string.error_server_connection)
                Loading -> CoinListState(isLoading = true)
                is Success -> CoinListState(coins = response.body)
            }
        }
    }
}
