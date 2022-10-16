package com.buginmyhead.lubangschoolhomework.weather.fundamental

@JvmInline
value class Temperature private constructor(val kelvin: Float) {

    val celsius: Float get() = kelvin - 273.15F
    val fahrenheit: Float get() = celsius * 9F / 5F + 32F

    companion object {

        fun fromKelvin(value: Float) = Temperature(maxOf(0F, value))
        fun fromCelsius(value: Float): Temperature = fromKelvin(value + 273.15F)
        fun fromFahrenheit(value: Float): Temperature = fromCelsius((value - 32F) * 5F / 9F)

    }

}