package com.example.final_project_04_22_2022.models.questions

class AllQuestions {
    private val allQuestions = mutableListOf<Question>()

    fun addQuestion(question: Question) {
        allQuestions.add(question)
    }

    fun numberOfQuestions() : Int {
        return allQuestions.size
    }
}