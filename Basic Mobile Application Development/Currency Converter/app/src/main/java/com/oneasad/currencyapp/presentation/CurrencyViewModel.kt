package com.oneasad.currencyapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oneasad.currencyapp.data.Currency
import com.oneasad.currencyapp.data.CurrencyRepository
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging

class CurrencyViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CurrencyState())
    val uiState: StateFlow<CurrencyState> = _uiState.asStateFlow()
    val TAG = "Asad Spy"
    private val httpClient = HttpClient(CIO) {
        expectSuccess = true

        engine {
            endpoint {
                keepAliveTime = 5000
                connectTimeout = 5000
                connectAttempts = 3
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }

        install(DefaultRequest) {
            header(HttpHeaders.ContentType, ContentType.Application.Json)
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.ALL
        }
    }

    private val currencyRepository by lazy {
        CurrencyRepository(httpClient = httpClient)
    }

    fun updateFromCurrency(currency: Currency) {
        _uiState.value = _uiState.value.copy(fromCurrency = currency)
    }

    fun updateToCurrency(currency: Currency) {
        _uiState.value = _uiState.value.copy(toCurrency = currency)
    }

    fun updateAmount(amount: String) {
        _uiState.value = _uiState.value.copy(amount = amount.toDoubleOrNull() ?: 0.0)
    }

    fun convert() {
        val fromCurrency = _uiState.value.fromCurrency.code
        val toCurrency = _uiState.value.toCurrency.code
        val amount = _uiState.value.amount

        if (amount <= 0) {
            _uiState.value = _uiState.value.copy(
                result = "Please enter a valid amount greater than 0."
            )
            return
        }

        viewModelScope.launch {
            try {
                val conversionResult = currencyRepository.convert(
                    fromCurrency = fromCurrency,
                    toCurrency = toCurrency,
                    amount = amount
                )

                _uiState.value = _uiState.value.copy(
                    result = (conversionResult ?: "Failed to fetch conversion rate.").toString()
                )
            } catch (e: Exception) {
                Log.e("CurrencyConverter", "Error fetching conversion rate", e)
                _uiState.value = _uiState.value.copy(
                    result = "An error occurred: ${e.message}. Please try again."
                )
            } finally {
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        }
    }
}
