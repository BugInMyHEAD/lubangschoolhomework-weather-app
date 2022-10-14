package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

import io.reactivex.rxjava3.core.Observable

interface ViewOutput<S, L, F> {

    val state: Observable<ViewState<S, L, F>>

}

