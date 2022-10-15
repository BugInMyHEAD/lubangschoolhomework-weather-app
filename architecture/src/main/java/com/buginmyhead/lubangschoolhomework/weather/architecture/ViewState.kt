package com.buginmyhead.lubangschoolhomework.weather.architecture

sealed class ViewState<S, L, F> {

    open val success: S? = null
    open val loading: L? = null
    open val failure: F? = null

    fun showState(presenter: Presenter<S, L, F>)  = when (this) {
        is Success -> presenter.onSuccess(success)
        is Loading -> presenter.onLoading(loading)
        is Failure -> presenter.onFailure(failure)
    }

    interface Presenter<S, L, F> {

        fun onSuccess(data: S)
        fun onLoading(data: L)
        fun onFailure(data: F)

    }

}

class Success<S, L, F>(override val success: S) : ViewState<S, L, F>()
class Loading<S, L, F>(override val loading: L) : ViewState<S, L, F>()
class Failure<S, L, F>(override val failure: F) : ViewState<S, L, F>()