package com.example.au_sek

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.au_sek.databinding.ActivityMain3Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain3Binding
    private val questions = arrayOf("Какое растение игрок получает первым?", "Какое растение умеет стрелять горохом?", "Какое рстение может защитить других от зомби?", "Какое растение может взорваться 3х3?", "Что делает Подсолнух?", "Какое растение умеет замораживать зомби?")
    private val options = arrayOf(
        arrayOf("Подсолнух", "Стенорех", "Горохострел"),
        arrayOf("Горохострел", "Подсолнух", "Боулинговая Луковица"),
        arrayOf("Стенорех", "Сияющая Фиалка", "Львиный Зев"),
        arrayOf("Вишнёвая Бомба", "Зимний Арбуз", "Вращающаяся Брюква"),
        arrayOf("Создаёт валюту - Солнца", "Подсвечивает поле", "Выплёвывает струю солнечного света"),
        arrayOf("Салат Айзберг", "Терпящий Дуриан", "Оба")
    )

    private val correctAnswers = arrayOf(0, 0, 0, 0, 0, 0)
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain3Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        displayQuestion()
        binding.option1.setOnClickListener{checkAnswer(0)}
        binding.option2.setOnClickListener{checkAnswer(1)}
        binding.option3.setOnClickListener{checkAnswer(2)}
        binding.restartButton.setOnClickListener{restartQuiz()}
        binding.aa.setOnClickListener {
            val Inten = Intent(this, MainActivity::class.java)
            startActivity(Inten)
        }
    }

    private fun correctButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1.setBackground(getDrawable(R.drawable.button_option_right))
            1 -> binding.option2.setBackground(getDrawable(R.drawable.button_option_right))
            2 -> binding.option3.setBackground(getDrawable(R.drawable.button_option_right))
        }
    }

    private fun wrongButtonColor(buttonIndex: Int) {
        when (buttonIndex) {
            0 -> binding.option1.setBackground(getDrawable(R.drawable.button_option_error))
            1 -> binding.option2.setBackground(getDrawable(R.drawable.button_option_error))
            2 -> binding.option3.setBackground(getDrawable(R.drawable.button_option_error))
        }
    }

    private fun resetButtonColor() {
        binding.option1.setBackground(getDrawable(R.drawable.button_option_neutral))
        binding.option2.setBackground(getDrawable(R.drawable.button_option_neutral))
        binding.option3.setBackground(getDrawable(R.drawable.button_option_neutral))
        binding.restartButton.setBackground(getDrawable(R.drawable.reset_inactive))
        binding.restartButton.setTextColor(Color.rgb(45, 4, 62))
    }


    private fun displayQuestion() {
        binding.questionText.text = questions[currentQuestionIndex]
        binding.option1.text = options[currentQuestionIndex][0]
        binding.option2.text = options[currentQuestionIndex][1]
        binding.option3.text = options[currentQuestionIndex][2]
        resetButtonColor()
    }

    private fun checkAnswer(selectedAnswerIndex: Int) {
        val correctAnswerIndex: Int = correctAnswers[currentQuestionIndex]
        if (selectedAnswerIndex == correctAnswerIndex) {
            score++
            correctButtonColor(selectedAnswerIndex)
        } else {
            wrongButtonColor(selectedAnswerIndex)
            correctButtonColor(correctAnswerIndex)
        }
        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            binding.questionText.postDelayed({ displayQuestion() }, 500)
        } else {
            binding.option1.isEnabled = false
            binding.option2.isEnabled = false
            binding.option3.isEnabled = false
            Toast.makeText(this, "Ваш результат $score из ${questions.size} вопросов", Toast.LENGTH_SHORT).show()
            binding.restartButton.isEnabled = true
            binding.restartButton.setBackground(getDrawable(R.drawable.button_option_error))
            binding.restartButton.setTextColor(Color.WHITE)
        }
    }

    private fun restartQuiz() {
        currentQuestionIndex = 0
        score = 0
        displayQuestion()
        binding.restartButton.isEnabled = false
        binding.option1.isEnabled = true
        binding.option2.isEnabled = true
        binding.option3.isEnabled = true
        binding.restartButton.setBackground(getDrawable(R.drawable.reset_inactive))
        binding.restartButton.setTextColor(Color.rgb(45, 4, 62))
    }
}