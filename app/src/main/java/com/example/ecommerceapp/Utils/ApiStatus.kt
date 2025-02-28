package com.example.ecommerceapp.Utils

sealed class ApiStatus<out T> {
    object Loading : ApiStatus<Nothing>()
    class Failure(val msg: String) : ApiStatus<Nothing>()
    class Success<out T>(val data: T) : ApiStatus<T>()
}
