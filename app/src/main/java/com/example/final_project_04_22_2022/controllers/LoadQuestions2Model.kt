package com.example.final_project_04_22_2022.controllers

import com.example.final_project_04_22_2022.models.questions.AllQuestions
import com.example.final_project_04_22_2022.models.questions.Question
import com.google.gson.Gson

class LoadQuestion2Model(question: Question) {

    var gson: Gson = Gson()

    var allQuestions: AllQuestions = AllQuestions()

}