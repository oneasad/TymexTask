package com.oneasad.currencyapp.presentation

import com.oneasad.currencyapp.data.Currency
import com.oneasad.currencyapp.data.currencyList

data class CurrencyState(
    val fromCurrency: Currency = Currency("United States Dollar", "USD"),
    val toCurrency: Currency = Currency("Pakistani Rupee", "PKR"),
    val amount: Double = 0.0,
    val result: String = "",
    val isLoading: Boolean = false,
    val allCurrencies: List<Currency> = currencyList
)