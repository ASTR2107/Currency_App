package com.example.currenycconverter.domain.model

sealed class Resourse<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T? = null): Resourse<T>(data)
    class Error<T>(message: String, data: T? = null): Resourse<T>(data, message)
}