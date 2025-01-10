package com.oneasad.currencyapp.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class CurrencyRepository(
    private val httpClient: HttpClient
){

    private val tag = "ExchangeRepositoryImpl: "

    private val baseUrl = "https://v6.exchangerate-api.com/v6"
    private val apiKey = "09f113ac8222e9b1eabbdb4a"

    suspend fun convert(
        fromCurrency: String, toCurrency: String, amount: Double
    ): Double {

        val result: ExchangeDto = httpClient.get(
            "$baseUrl/$apiKey/pair/$fromCurrency/$toCurrency/$amount"
        ).body()

        println(tag + result.conversion_result)

        return result.conversion_result
//        return 243.61
    }

     suspend fun getAllCurrencies(): List<Currency> {
        return currencyList
    }
}

