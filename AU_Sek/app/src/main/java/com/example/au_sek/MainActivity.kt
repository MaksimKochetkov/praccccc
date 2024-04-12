package com.example.au_sek

import android.app.Activity
import android.content.Intent
import android.graphics.Insets
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.au_sek.databinding.ActivityMainBinding
import kotlinx.coroutines.Runnable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isRunning = false
    private var timerSeconds = 0
    private val handler = Handler(Looper.getMainLooper())

    private val runnable = object : Runnable {
        override fun run() {
            timerSeconds++
            val hours: Int = timerSeconds / 3600
            val minutes: Int = (timerSeconds % 3600) / 60
            val seconds: Int = timerSeconds % 60
            val time: String = String.format("%02d:%02d:%02d", hours, minutes, seconds)
            binding.textView.text = time
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Thread.sleep(3000)
        installSplashScreen()
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startBtn.setOnClickListener{
            startTimer()
        }
        binding.stopBtn.setOnClickListener{
            stopTimer()
        }
        binding.resetBtn.setOnClickListener{
            resetTimer()
        }
        binding.textView2.setOnClickListener {
            val Inten = Intent(this, MainActivity2::class.java)
            startActivity(Inten)
        }
    }

    private fun startTimer() {
            if (!isRunning) {
                handler.postDelayed(runnable, 1000)
                isRunning = true
                binding.startBtn.isEnabled = false
                binding.stopBtn.isEnabled = true
                binding.resetBtn.isEnabled = true
        }
    }

    private fun stopTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable)
            isRunning = false
            binding.startBtn.isEnabled = true
            binding.startBtn.text = "возобновить"
            binding.stopBtn.isEnabled = false
            binding.resetBtn.isEnabled = true
        }
    }
    private fun resetTimer() {
        stopTimer()
        timerSeconds = 0
        binding.textView.text = "00:00:00"
        binding.startBtn.isEnabled = true
        binding.stopBtn.isEnabled = false
        binding.startBtn.text = "запустить"
        binding.resetBtn.isEnabled = false
    }
}