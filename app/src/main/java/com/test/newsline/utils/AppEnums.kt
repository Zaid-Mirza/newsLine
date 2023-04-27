package com.test.newsline.utils

class AppEnums {

    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    enum class ErrorType {
        Token, RefreshToken, Network, Service
    }
}