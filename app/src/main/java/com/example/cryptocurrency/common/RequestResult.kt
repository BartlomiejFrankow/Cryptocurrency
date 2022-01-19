package com.example.cryptocurrency.common

sealed class RequestResult<out BODY> {
    object HttpException : RequestResult<Nothing>()
    object IOException : RequestResult<Nothing>()
    object Loading : RequestResult<Nothing>()
    data class Success<T>(val body: T) : RequestResult<T>()
}
