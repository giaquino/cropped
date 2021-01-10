package com.giaquino.android.cropped.data.common

class Resource<T> private constructor(
        private val data: T?,
        private val error: Throwable?,
        private val status: Status) {

    enum class Status {
        SUCCESS, FAILURE, LOADING
    }

    fun isSuccess(accept: (T) -> Unit) {
        if (status == Status.SUCCESS) accept(data!!)
    }

    fun isFailure(accept: (Throwable) -> Unit) {
        if (status == Status.FAILURE) accept(error!!)
    }

    fun isLoading(accept: (T?) -> Unit) {
        if (status == Status.LOADING) accept(data)
    }

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(data, null, Status.SUCCESS)
        }

        fun <T> failure(error: Throwable): Resource<T> {
            return Resource(null, error, Status.FAILURE)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(data, null, Status.LOADING)
        }
    }
}