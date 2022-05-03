package com.example.final_project_04_22_2022.models.score

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

//    private var score: MutableLiveData<Int> = MutableLiveData(0)
    private var score: Int = 0
//    fun getScore() : LiveData<Int> {
//        return score
//    }
//    fun zero() {
//        score.value = 0
//    }

//    fun inc(){
//        score.value = score.value?.plus(5)
//
//    }
//
//    fun dec(){
//        score.value = score.value?.minus(3)
//    }
    fun resetScore() {
        score = 0
    }
    fun inc(): Int{
        score += 5
        return score
    }

    fun dec(): Int {
        score -= 3
        return score
    }
}