package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var imStart: ImageButton
    lateinit var imPause: ImageButton
    lateinit var imReset: ImageButton

    var seconds = 0
     var running: Boolean = false
     var lastRunningState: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imStart = findViewById(R.id.imStart)
        imPause = findViewById(R.id.imPause)
        imReset = findViewById(R.id.imReset)

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            lastRunningState = savedInstanceState.getBoolean("lastRunningState")
        }

        runStopWatch()
    }

    private fun runStopWatch() {
        val txtTime: TextView = findViewById(R.id.txtTime)
        val handler = Handler()

        val runnable = object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60

                val time = String.format(Locale.getDefault(), "%d:%02d:%02d", hours, minutes, secs)
                txtTime.text = time

                if (running) {
                    seconds++
                }

                handler.postDelayed(this, 1000)
            }
        }

        handler.post(runnable)
    }


    fun onClickStart(view: View) {
        if (imReset.visibility == View.VISIBLE) {
            imReset.visibility = View.INVISIBLE
        }
        running = true
        imStart.visibility = View.INVISIBLE
        imPause.visibility = View.VISIBLE
    }

    fun onClickStop(view: View) {
        running = false
        imPause.visibility = View.INVISIBLE
        imStart.visibility = View.VISIBLE
        imReset.visibility = View.VISIBLE

    }

    fun onClickReset(view: View) {
        running = false
        seconds = 0
        imReset.visibility = View.INVISIBLE
    }

}