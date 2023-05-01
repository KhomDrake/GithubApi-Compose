package com.vlv.githubapicompose.domain.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class Response<T> {

    private var response by mutableStateOf(DataResponse<T>())

    val state: StateResponse
        get() = response.state ?: StateResponse.LOADING

    val data: T?
        get() = response.data

    val error: Throwable?
        get() = response.error

    suspend fun requestData(func: suspend () -> T) {
        runCatching {
            postLoading()
            val data = func.invoke()
            data?.let {
                postData(it)
            } ?: postError(Throwable())
        }.onFailure {
            postError(it)
        }
    }

    fun postData(data: T?) = apply {
        response = response.copy(data = data, state = StateResponse.SUCCESS)
    }

    fun postError(throwable: Throwable) = apply {
        response = response.copy(error = throwable, state = StateResponse.ERROR)
    }

    fun postLoading() = apply {
        response = response.copy(state = StateResponse.LOADING)
    }

}

data class DataResponse<T>(
    val data: T? = null,
    val state: StateResponse? = null,
    val error: Throwable? = null
) {


}

enum class StateResponse {
    SUCCESS,
    LOADING,
    ERROR
}