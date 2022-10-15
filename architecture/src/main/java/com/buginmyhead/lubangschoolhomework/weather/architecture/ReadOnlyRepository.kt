package com.buginmyhead.lubangschoolhomework.weather.architecture

import io.reactivex.rxjava3.core.Single

interface ReadOnlyRepository<T : Any> {

    val value: Single<T>

}