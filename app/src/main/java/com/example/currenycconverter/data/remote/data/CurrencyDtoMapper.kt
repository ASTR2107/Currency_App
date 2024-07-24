package com.example.currenycconverter.data.remote.data

import com.example.currenycconverter.domain.model.ExchangeRate

fun CurrencyData.toExchangeRate(): List<ExchangeRate> {
    val currencyData = this.data
    return listOf(
        ExchangeRate("INR", "Российский руюль", currencyData.INR),
        ExchangeRate("INR", "Indian Rupee", currencyData.INR),
        ExchangeRate("EUR", "Euro", currencyData.EUR),
        ExchangeRate("USD", "US Dollar", currencyData.USD),
        ExchangeRate("JPY", "Japanese Yen", currencyData.JPY),
        ExchangeRate("BGN", "Bulgarian Lev", currencyData.BGN),
        ExchangeRate("CZK", "Czech Republic Koruna", currencyData.CZK),
        ExchangeRate("DKK", "Danish Krone", currencyData.DKK),
        ExchangeRate("GBP", "British Pound Sterling", currencyData.GBP),
        ExchangeRate("HUF", "Hungarian Forint", currencyData.HUF),
        ExchangeRate("PLN", "Polish Zloty", currencyData.PLN),
        ExchangeRate("RON", "Romanian Leu", currencyData.RON),
        ExchangeRate("SEK", "Swedish Krona", currencyData.SEK),
        ExchangeRate("CHF", "Swiss Franc", currencyData.CHF),
        ExchangeRate("ISK", "Icelandic Króna", currencyData.ISK),
        ExchangeRate("NOK", "Norwegian Krone", currencyData.NOK),
        ExchangeRate("HRK", "Croatian Kuna", currencyData.HRK),
        ExchangeRate("RUB", "Russian Ruble", currencyData.RUB),
        ExchangeRate("TRY", "Turkish Lira", currencyData.TRY),
        ExchangeRate("AUD", "Australian Dollar", currencyData.AUD),
        ExchangeRate("BRL", "Brazilian Real", currencyData.BRL),
        ExchangeRate("CAD", "Canadian Dollar", currencyData.CAD),
        ExchangeRate("CNY", "Chinese Yuan", currencyData.CNY),
        ExchangeRate("HKD", "Hong Kong Dollar", currencyData.HKD),
        ExchangeRate("IDR", "Indonesian Rupiah", currencyData.IDR),
        ExchangeRate("ILS", "Israeli New Sheqel", currencyData.ILS),
        ExchangeRate("KRW", "South Korean Won", currencyData.KRW),
        ExchangeRate("MXN", "Mexican Peso", currencyData.MXN),
        ExchangeRate("MYR", "Malaysian Ringgit", currencyData.MYR),
        ExchangeRate("NZD", "New Zealand Dollar", currencyData.NZD),
        ExchangeRate("PHP", "Philippine Peso", currencyData.PHP),
        ExchangeRate("SGD", "Singapore Dollar", currencyData.SGD),
        ExchangeRate("THB", "Thai Baht", currencyData.THB),
        ExchangeRate("ZAR", "South African Rand", currencyData.ZAR),
    )
}