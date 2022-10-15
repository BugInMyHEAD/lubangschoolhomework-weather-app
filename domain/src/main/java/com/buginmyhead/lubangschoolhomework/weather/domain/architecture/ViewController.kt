package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

import io.reactivex.rxjava3.core.Observable

interface ViewController<S, L, F> {

    val output: Observable<ViewState<S, L, F>>

    fun switchToSuccess(data: S)
    fun switchToLoading(data: L)
    fun switchToFailure(data: F)

}