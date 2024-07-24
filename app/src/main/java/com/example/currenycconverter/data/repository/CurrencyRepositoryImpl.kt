package com.example.currenycconverter.data.repository

import com.example.currenycconverter.data.local.entity.CurrencyDao
import com.example.currenycconverter.data.local.entity.toCurrencyEntity
import com.example.currenycconverter.data.local.entity.toExchangeRate
import com.example.currenycconverter.data.remote.CurrencyApi
import com.example.currenycconverter.data.remote.data.toExchangeRate
import com.example.currenycconverter.domain.model.ExchangeRate
import com.example.currenycconverter.domain.model.Resourse
import com.example.currenycconverter.domain.repository.CurrencyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException

class CurrencyRepositoryImpl(
    private val api: CurrencyApi,
    private val dao: CurrencyDao
):CurrencyRepository {
    override fun getListCurrent(): Flow<Resourse<List<ExchangeRate>>> = flow {
        val localCurrencyRates = getStateCurrency()
        emit(Resourse.Success(localCurrencyRates))

        try {
            val newRates = getRemoteCurrency()
            updateCurrencyRate(newRates)
            emit(Resourse.Success(newRates))
        }catch (e: IOException) {
            emit(
                Resourse.Error(
                    message = "Что-то пошло не так...",
                    data = localCurrencyRates
                )
            )
        }catch (e: Exception) {
            emit(
                Resourse.Error(
                    message = "Так, сейчас исправим!",
                    data = localCurrencyRates
                )
            )
        }

    }
    private suspend fun getStateCurrency(): List<ExchangeRate> {
        return dao.getAllCurrency().map { it.toExchangeRate() }
    }
    private suspend fun getRemoteCurrency(): List<ExchangeRate> {
        val response = api.getLatest()
        return response.toExchangeRate()
    }
    private suspend fun updateCurrencyRate(exchangeRate: List<ExchangeRate>) {
        dao.upsertAll(exchangeRate.map { it.toCurrencyEntity() })
    }
}