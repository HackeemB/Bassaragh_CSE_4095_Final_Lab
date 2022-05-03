package com.example.final_project_04_22_2022.models.questions

class AnswerList(lst: List<AnswerObject>) {
    private var answerList = mutableListOf<AnswerObject>()

    fun numberOfAnswers() : Int {
        return answerList.size
    }

    fun setAnswerList(aList: List<AnswerObject>) {
        answerList = aList.toMutableList()
    }

    fun getAnswer(): String {
        var range: Int = numberOfAnswers()

        for (i in 0..range) {
            if (answerList[i].getIsTrue() == "true") {
                return answerList[i].getIsTrue()
            }
            else {
                return "false"
            }
        }
        return "false"
    }

    fun addAnswer(answerString: String, isTrue: String) {
        answerList.add(AnswerObject(answerString,isTrue))
    }
}