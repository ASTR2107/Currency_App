package com.example.currenycconverter.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CurrencyEntity(
    @PrimaryKey
    val code: String,
    val currency: String,
    val rate: Double
)
