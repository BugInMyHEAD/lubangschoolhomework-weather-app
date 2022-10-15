package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

import io.reactivex.rxjava3.subjects.Subject

abstract class AbstractViewController<S, L, F> : ViewController<S, L, F> {

    abstract override val output: Subject<ViewState<S, L, F>>

    override fun switchToSuccess(data: S) {
        output.onNext(Success(data))
    }

    override fun switchToLoading(data: L) {
        output.onNext(Loading(data))
    }

    override fun switchToFailure(data: F) {
        output.onNext(Failure(data))
    }

}