package com.example.currenycconverter.data.local.entity

import androidx.room.Dao
import androidx.room.Upsert

@Dao
interface CurrencyDao {
    @Upsert
    suspend fun upsertAll(currencyEntity: List<CurrencyEntity>)

    @androidx.room.Query("SELECT * FROM currencyentity")
    suspend fun getAllCurrency(): List<CurrencyEntity>
}