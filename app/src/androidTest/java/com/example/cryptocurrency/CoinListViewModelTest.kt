package com.example.cryptocurrency

import com.example.cryptocurrency.presentation.coinList.CoinListViewModel
import com.example.domain.common.RequestResult
import com.example.domain.model.Coin
import com.example.domain.repository.CoinRepository
import com.example.domain.useCase.getCoins.GetCoinsUseCase
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito

class CoinListViewModelTest {

    private val successResponse = RequestResult.Success(successCoinsList())
    private val httpExceptionResponse = RequestResult.HttpException
    private val ioExceptionResponse = RequestResult.IOException
    private val loading = RequestResult.Loading

    private val vm = CoinListViewModel(GetCoinsUseCase(Mockito.mock(CoinRepository::class.java)))

    @Test
    fun `on get coin list success`() {
        vm.getCoins(successResponse)
        assertEquals(3, vm.state.value.coins.size)
    }

    @Test
    fun `on get coin list http exception`() {
        vm.getCoins(httpExceptionResponse)
        assertEquals(R.string.error_unexpected, vm.state.value.error)
    }

    @Test
    fun `on get coin list io exception`() {
        vm.getCoins(ioExceptionResponse)
        assertEquals(R.string.error_server_connection, vm.state.value.error)
    }

    @Test
    fun `on get coin list loading`() {
        vm.getCoins(loading)
        assertEquals(true, vm.state.value.isLoading)
    }

    private fun successCoinsList() = listOf(
        Coin("1", true, "Bitcoin", 5, "BT"),
        Coin("2", false, "Ethereum", 5, "ETH"),
        Coin("3", true, "Tether", 5, "USDT"),
    )
}
