package com.example.quiz

import android.graphics.Color.GREEN
import android.graphics.Color.RED
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isGone
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var quiz: Quiz

    lateinit var trueButton: Button
    lateinit var falseButton: Button
    lateinit var text: TextView
    lateinit var pointTracker: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        val inputStream = resources.openRawResource(R.raw.questions)
        val jsonText = inputStream.bufferedReader().use {
            it.readText()
        }
        Log.d(TAG,"onCreate: $jsonText")

        val gson = Gson()
        val type = object : TypeToken<List<Question>>() { }.type
        val questions = gson.fromJson<List<Question>>(jsonText, type)
        Log.d(TAG, "onCreate: \n${questions.toString()}")

        quiz = Quiz(questions)

        text.setText(quiz.quest.get(0).question)
        pointTracker.setText("Points: " + 0)

        trueButton.setOnClickListener {
            quiz.checkAnswer(true)
            updateQuestion()
        }

        falseButton.setOnClickListener {
            quiz.checkAnswer(false)
            updateQuestion()
        }
    }

    fun end() {
            trueButton.visibility = View.GONE
            falseButton.visibility = View.GONE
            pointTracker.visibility = View.GONE
            text.setText("Congratulations! You got " + quiz.score() + " points!")
    }

    fun updateQuestion(){
        if(!quiz.moreQuestions()){
            end()
        }
        else {
            pointTracker.setText("Points: " + quiz.score())
            text.setText(quiz.updateQuestion().question)
        }
    }

    data class Question(val question: String , val answer: Boolean){
    }

    private fun wireWidgets() {
        trueButton = findViewById(R.id.button_main_true)
        falseButton = findViewById(R.id.button_main_false)
        text = findViewById(R.id.TextView_main_question)
        pointTracker = findViewById(R.id.textView_main_points)
    }
}