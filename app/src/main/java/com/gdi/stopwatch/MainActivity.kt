package com.gdi.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var timeTextView: TextView? = null
    var startButton: Button? = null

    var isTimerRunning: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeTextView = findViewById(R.id.tv_time)
        startButton = findViewById(R.id.btn_start)

        startButton?.setOnClickListener {
            isTimerRunning = !isTimerRunning
            startButton?.setText(if (isTimerRunning) "Stop" else "Start")
        }
    }
}
