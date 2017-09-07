package com.gdi.stopwatch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var timeTextView: TextView? = null
    var startButton: Button? = null

    var isTimerRunning: Boolean = false
    var startTime: Long? = null
    var timerHandler: Handler = Handler()
    var timerAction: (() -> Unit) = {
        if (isTimerRunning) updateTimerText()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeTextView = findViewById(R.id.tv_time)
        startButton = findViewById(R.id.btn_start)

        startButton?.setOnClickListener {
            isTimerRunning = !isTimerRunning
            if (isTimerRunning) {
                startTime = System.currentTimeMillis()
                timerHandler.postDelayed(timerAction, 0)
            } else {
                timerHandler.removeCallbacks(timerAction)
            }
            startButton?.setText(if (isTimerRunning) "Stop" else "Start")
        }
    }

    fun updateTimerText() {
        val currentTime = System.currentTimeMillis()
        val timeSince = currentTime - (startTime ?: currentTime)
        timeTextView?.setText(timeSince.timeIntervalString())
        timerHandler.postDelayed(timerAction, 30)
    }
}

fun Long.toCentiseconds() = this / 10
fun Long.toSeconds() = this / 1000
fun Long.toMinutes() = this.toSeconds() / 60
fun Long.timeIntervalString() = String.format("%02d:%02d:%02d", this.toMinutes(), this.toSeconds() % 60, this.toCentiseconds() % 100)
