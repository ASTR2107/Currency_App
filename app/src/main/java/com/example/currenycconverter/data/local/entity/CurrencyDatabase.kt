package com.example.currenycconverter.data.local.entity

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CurrencyEntity::class],
    version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract val currencyDao: CurrencyDao
}