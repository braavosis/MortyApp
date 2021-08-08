package com.app.mortyapp

data class Resource<out T> (
    val status: NetworkStatus,
    val data: T? = null,
    val message: String? = null
        )

enum class NetworkStatus{
    LOADING,
    SUCCESS,
    ERROR
}