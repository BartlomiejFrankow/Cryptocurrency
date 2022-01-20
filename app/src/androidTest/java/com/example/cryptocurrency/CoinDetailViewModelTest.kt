package com.example.cryptocurrency


import androidx.lifecycle.SavedStateHandle
import com.example.cryptocurrency.presentation.coinDetail.CoinDetailViewModel
import com.example.domain.common.RequestResult
import com.example.domain.model.CoinDetail
import com.example.domain.repository.CoinRepository
import com.example.domain.useCase.getCoin.GetCoinUseCase
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CoinDetailViewModelTest {

    private val coinDetail = CoinDetail(
        coinId = "1",
        name = "Bitcoin",
        description = "Awesome",
        symbol = "BT",
        rank = 1,
        isActive = true,
        tags = listOf(),
        team = listOf()
    )

    private val successResponse = RequestResult.Success(coinDetail)
    private val httpExceptionResponse = RequestResult.HttpException
    private val ioExceptionResponse = RequestResult.IOException
    private val loading = RequestResult.Loading

    private val useCase = GetCoinUseCase(Mockito.mock(CoinRepository::class.java))
    private val vm = CoinDetailViewModel(useCase, SavedStateHandle())

    @Test
    fun `on get coin success`() {
        vm.getCoin(successResponse)
        assertTrue(vm.state.value.coin == coinDetail)
    }

    @Test
    fun `on get coin http exception`() {
        vm.getCoin(httpExceptionResponse)
        Assert.assertEquals(R.string.error_unexpected, vm.state.value.error)
    }

    @Test
    fun `on get coin io exception`() {
        vm.getCoin(ioExceptionResponse)
        Assert.assertEquals(R.string.error_server_connection, vm.state.value.error)
    }

    @Test
    fun `on get coin loading`() {
        vm.getCoin(loading)
        Assert.assertEquals(true, vm.state.value.isLoading)
    }
}
