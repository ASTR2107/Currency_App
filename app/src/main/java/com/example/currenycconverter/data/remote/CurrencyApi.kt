package com.example.currenycconverter.data.remote

import com.example.currenycconverter.data.remote.data.CurrencyData
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("v1/latest")
    suspend fun getLatest(@Query("apiKey") apiKey: String = API_KEY): CurrencyData

    companion object {
        const val API_KEY = "fca_live_Uoo0vS3sEc1gLc1i0VpZzccDRETSYhqt7LO9pmdI"
        const val BASE_URL = "https://api.freecurrencyapi.com"
    }
}