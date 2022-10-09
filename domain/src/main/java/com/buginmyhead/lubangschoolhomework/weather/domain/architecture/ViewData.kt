package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

sealed class ViewData<S, L, F> {

    open val success: S? = null
    open val loading: L? = null
    open val failure: F? = null

    fun showState(handler: Presenter<S, L, F>)  = when (this) {
        is Success -> handler.onSuccess(success)
        is Loading -> handler.onLoading(loading)
        is Failure -> handler.onFailure(failure)
    }

    interface Presenter<S, L, F> {

        fun onSuccess(data: S)
        fun onLoading(data: L)
        fun onFailure(data: F)

    }

}

class Success<S, L, F>(override val success: S) : ViewData<S, L, F>()
class Loading<S, L, F>(override val loading: L) : ViewData<S, L, F>()
class Failure<S, L, F>(override val failure: F) : ViewData<S, L, F>()