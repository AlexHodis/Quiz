package com.example.quiz

import android.annotation.SuppressLint
import android.graphics.Color.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var quiz: Quiz

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var text: TextView
    private lateinit var pointTracker: TextView
    private lateinit var backgroundColor: FrameLayout
    private var response = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        backgroundColor.setBackgroundColor(rgb(255, 255, 204))

        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG,"onCreate: $jsonText")

        val gson = Gson()
        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonText, type)
        Log.d(TAG, "onCreate: \n${questions}")

        quiz = Quiz(questions)

        text.text = quiz.quest[0].question
        pointTracker.text = resources.getString(R.string.main_point) + " 0"

        trueButton.setOnClickListener {
            response = quiz.checkAnswer(true)
            graphics()
            updateQuestion()
        }

        falseButton.setOnClickListener {
            response = quiz.checkAnswer(false)
            graphics()
            updateQuestion()
        }
    }

    private fun graphics() {
        if (response) {
            trueButton.setBackgroundColor(GREEN)
            falseButton.setBackgroundColor(GREEN)
        } else {
            trueButton.setBackgroundColor(RED)
            falseButton.setBackgroundColor(RED)
        }
    }


    @SuppressLint("SetTextI18n")
    private fun end() {
            trueButton.visibility = View.GONE
            falseButton.visibility = View.GONE
            pointTracker.visibility = View.GONE
            text.text = resources.getString(R.string.main_end) + " " + quiz.score() + " " +
                    resources.getString(R.string.main_points) + "!"
    }

    @SuppressLint("SetTextI18n")
    private fun updateQuestion(){
        if(!quiz.moreQuestions()){
            end()
        }
        else {
            pointTracker.text = resources.getString(R.string.main_point)+ " " + quiz.score()
            text.text = quiz.updateQuestion().question
        }
    }

    private fun wireWidgets() {
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        text = findViewById(R.id.textView_main_question)
        pointTracker = findViewById(R.id.textView_main_points)
        backgroundColor = findViewById(R.id.frameLayout_main_color)
    }
}