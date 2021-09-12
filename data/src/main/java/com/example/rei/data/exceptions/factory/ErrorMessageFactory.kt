package com.example.rei.data.exceptions.factory

import com.example.rei.data.exceptions.ConnectionLostException


class ErrorMessageFactory {

    companion object {
        fun create(throwable: Throwable) : String? {
            if (throwable is ConnectionLostException) {
                return "Проверьте интернет соединение"
            }
            return throwable.localizedMessage
        }
    }
}