package com.jane.practice.Motty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jane.practice.Motty.ui.main.overview.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment())
                    .commitNow()
        }
    }
}