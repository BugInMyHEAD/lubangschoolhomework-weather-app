package com.buginmyhead.lubangschoolhomework.weather.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.buginmyhead.lubangschoolhomework.weather.app.R
import com.buginmyhead.lubangschoolhomework.weather.app.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

}