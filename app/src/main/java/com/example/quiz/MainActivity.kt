package com.example.quiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var trueButton: Button
    lateinit var falseButton: Button
    lateinit var text: TextView
    var questionNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        trueButton.setOnClickListener {

        }

        falseButton.setOnClickListener {

        }
    }

    data class Questions(var question: String , var answer: Boolean)
    data class Quiz(var questions: List<Questions>)

    private fun wireWidgets() {
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        text = findViewById(R.id.TextView_main_question)
    }
}