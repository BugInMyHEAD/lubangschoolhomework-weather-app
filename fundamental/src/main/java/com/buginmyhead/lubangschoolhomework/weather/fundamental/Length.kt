package com.buginmyhead.lubangschoolhomework.weather.fundamental

@JvmInline
value class Length private constructor(
    val nanometers: Double
) {

    val micrometers: Double get() = nanometers / 1_000.0
    val millimeters: Double get() = nanometers / 1_000_000.0
    val meters: Double get() = nanometers / 1_000_000_000.0

    companion object {

        val Number.nanometers get() = Length(toDouble())
        val Number.micrometers get() = Length(toDouble() * 1_000.0)
        val Number.millimeters get() = Length(toDouble() * 1_000_000.0)
        val Number.meters get() = Length(toDouble() * 1_000_000_000.0)

    }

}