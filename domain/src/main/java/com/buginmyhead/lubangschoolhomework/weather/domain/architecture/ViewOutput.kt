package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

import io.reactivex.rxjava3.core.Flowable

interface ViewOutput<S, L, F> {

    val state: Flowable<ViewData<S, L, F>>

}

