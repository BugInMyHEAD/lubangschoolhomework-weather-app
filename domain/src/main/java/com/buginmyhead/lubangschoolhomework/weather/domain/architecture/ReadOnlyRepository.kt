package com.buginmyhead.lubangschoolhomework.weather.domain.architecture

import io.reactivex.rxjava3.core.Single

interface ReadOnlyRepository<T : Any> {

    val value: Single<T>

}