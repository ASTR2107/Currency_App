package com.example.currenycconverter.di

import android.app.Application
import androidx.room.Room
import com.example.currenycconverter.data.local.entity.CurrencyDatabase
import com.example.currenycconverter.data.remote.CurrencyApi
import com.example.currenycconverter.data.repository.CurrencyRepositoryImpl
import com.example.currenycconverter.domain.repository.CurrencyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyAppModule{

    @Provides
    @Singleton
    fun provideApiCurrency(): CurrencyApi{
        val retrofit = Retrofit
            .Builder()
            .baseUrl(CurrencyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CurrencyApi::class.java)
    }
    @Singleton
    @Provides
    fun provideCurrencyDatabase(application: Application): CurrencyDatabase{
        return Room
            .databaseBuilder(
                application,
                CurrencyDatabase::class.java,
                "exchange"
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideRepository(
        api: CurrencyApi,
        db: CurrencyDatabase
    ): CurrencyRepository {
        return CurrencyRepositoryImpl(
            api = api,
            dao = db.currencyDao
        )
    }

}