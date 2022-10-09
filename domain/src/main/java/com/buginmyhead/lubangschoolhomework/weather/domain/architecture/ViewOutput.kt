package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableSource

interface ViewOutput<S, L, F> {

    val state: ObservableSource<ViewData<S, L, F>>

}

