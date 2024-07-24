package com.example.currenycconverter.View

import com.example.currenycconverter.domain.model.ExchangeRate

data class ScreenState(
    val currencyCode: String = "INR",
    val toCurrencyCode: String = "USD",
    val currencyValue: String = "0.00",
    val toCurrencyValue: String = "0.00",
    val selection: SelectionState = SelectionState.FROM,
    val exchangeRates: Map<String, ExchangeRate> = emptyMap(),
    val error: String? = null
)
enum class SelectionState {
    FROM,
    TO
}
