package com.example.final_project_04_22_2022.models.myquestions

class Question(private var questionIndex: Int, private var isQuestionTrue: Boolean,private var imageName: String = "")  {

    public var index = questionIndex
    public var isTrue = isQuestionTrue
}