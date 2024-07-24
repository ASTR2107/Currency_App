package com.example.currenycconverter.domain.repository

import com.example.currenycconverter.domain.model.ExchangeRate
import com.example.currenycconverter.domain.model.Resourse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepository {
    fun getListCurrent(): Flow<Resourse<List<ExchangeRate>>>
}