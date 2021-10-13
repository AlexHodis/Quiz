package com.example.quiz

class Quiz(val quest: List<MainActivity.Question>){
    private var questionNum = 0
    private var points = 0

    fun checkAnswer(answer : Boolean) : Boolean {
        return if (answer == quest[questionNum].answer){
            points++
            questionNum++
            true
        } else{
            questionNum++
            false
        }
    }

    fun updateQuestion() : MainActivity.Question {
        return quest[questionNum]
    }

    fun moreQuestions() : Boolean{
        return (questionNum < 15)
    }

    fun score() : Int{
        return points
    }
    }
