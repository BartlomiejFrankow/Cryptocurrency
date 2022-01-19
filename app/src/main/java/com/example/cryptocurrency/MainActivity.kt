package com.example.cryptocurrency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.presentation.Screen
import com.example.cryptocurrency.presentation.coinDetail.CoinDetailScreen
import com.example.cryptocurrency.presentation.coinList.CoinListScreen
import com.example.cryptocurrency.presentation.ui.theme.CryptocurrencyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinListScreen.route
                    ) {
                        composable(route = Screen.CoinListScreen.route) { CoinListScreen(navController = navController) }
                        composable(route = Screen.CoinDetailsScreen.route + "/{coinId}") { CoinDetailScreen(navController = navController) }
                    }
                }
            }
        }
    }
}
