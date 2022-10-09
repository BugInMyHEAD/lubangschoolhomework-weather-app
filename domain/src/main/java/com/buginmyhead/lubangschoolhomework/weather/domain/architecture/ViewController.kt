package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

import io.reactivex.rxjava3.processors.FlowableProcessor

interface ViewController<S, L, F> : ViewOutput<S, L, F> {

    override val state: FlowableProcessor<ViewData<S, L, F>>

    fun switchToSuccess(data: S) {
        state.onNext(Success(data))
    }

    fun switchToLoading(data: L) {
        state.onNext(Loading(data))
    }

    fun switchToFailure(data: F) {
        state.onNext(Failure(data))
    }

}