package com.buginmyhead.lubangschoolhomework.weather.fundamental

@JvmInline
value class Temparature private constructor(val kelvin: Float) {

    val celsius: Float get() = kelvin - 273.15F
    val fahrenheit: Float get() = celsius * 9F / 5F + 32F

    companion object {

        fun fromKelvin(value: Float) = Temparature(maxOf(0F, value))
        fun fromCelsius(value: Float): Temparature = fromKelvin(value + 273.15F)
        fun fromFahrenheit(value: Float): Temparature = fromCelsius((value - 32F) * 5F / 9F)

    }

}