package com.example.currenycconverter.data.local.entity

import com.example.currenycconverter.domain.model.ExchangeRate


fun CurrencyEntity.toExchangeRate(): ExchangeRate {
    return ExchangeRate(
        code = code,
        name = currency,
        rate = rate
    )
}

fun ExchangeRate.toCurrencyEntity(): CurrencyEntity {
    return CurrencyEntity(
        code = code,
        currency = name,
        rate = rate
    )
}