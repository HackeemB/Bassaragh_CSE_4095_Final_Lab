package com.example.final_project_04_22_2022.models.questions

class AnswerObject(var answerString: String, var isAnswerTrue: String) {

    private var answer = ""
    private var isTrue = ""

    fun setAnswer(aString: String) {
        answer = aString
    }
    fun getAnswer() : String {
        return answer
    }

    fun setIsTrue(bValue: String) {
        isTrue = bValue
    }
    fun getIsTrue(): String {
        return isTrue
    }

}