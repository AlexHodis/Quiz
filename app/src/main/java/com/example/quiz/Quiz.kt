package com.example.quiz

import android.graphics.Color
class Quiz(val quest: List<MainActivity.Question>){
    var questionNum = 0
    var points = 0

    fun checkAnswer(answer : Boolean) : Boolean {
        if (answer == quest.get(questionNum).answer){
            points++
            questionNum++
        return answer
    }
        else{
            questionNum++
            return false
        }
    }

    fun updateQuestion() : MainActivity.Question {
        return quest.get(questionNum)
    }

    fun moreQuestions() : Boolean{
        return (questionNum < 15)
    }

    fun score() : Int{
        return points
    }

    }
