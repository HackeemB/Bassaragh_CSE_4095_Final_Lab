package com.example.final_project_04_22_2022

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.final_project_04_22_2022.models.questions.AnswerList
import com.example.final_project_04_22_2022.models.questions.AnswerObject
import com.example.final_project_04_22_2022.models.questions.Question
import com.example.final_project_04_22_2022.models.score.ScoreViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var getDataButton: Button
//    private lateinit var getImageButton: Button
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var basicQuestionView: TextView
    private lateinit var scoreView: TextView
    private lateinit var imageView: ImageView

    private var gson = Gson()

    //  UCONN IP -- 10.194.81.108
    // Myano IP -- 192.168.1.26

    val urlJSON = "http://192.168.1.26:8080/questions" //192.168.1.7 // 69.126.152.4
//    val urlIMAGE = "http://192.168.1.26:8080/static/drawing_sharks.jpg"; //192.168.1.7
    val urlIMAGE2 = "http://192.168.1.26:8080/static/" //192.168.1.7
    var fullImgStr: String = ""
    val maxQuestions = 5
    var count: Int = 0
    var qNum: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataButton = findViewById(R.id.get_data_button)
//        getImageButton = findViewById(R.id.image_button)
        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        basicQuestionView = findViewById(R.id.basic_question_view)
        scoreView = findViewById(R.id.score_view)
        imageView = findViewById(R.id.imageView)

        var score = 0;
        val checkScore: ScoreViewModel =  ScoreViewModel()


        var globalAnswer: String = ""

        getDataButton.setOnClickListener {

// ...

// Instantiate the RequestQueue.
            val queue = Volley.newRequestQueue(this)


            val jsonArrayRequest = JsonArrayRequest(Request.Method.GET,
                urlJSON,
                null,
                { response ->
                    // Display the first 500 characters of the response string.
//                    basicQuestionView.setText("Response is: ${response}")


                    var questionList: List<Question> =
                        gson.fromJson(response.toString(), Array<Question>::class.java).toList()

//                    var aList: AnswerList = gson.fromJson(response.toString(), AnswerList::class.java )


                    if (qNum >= 6) {
//                        if (count == maxQuestions) {
                        scoreView.setText("All done! $score is your final score!")
                        basicQuestionView.setText("Click \"Question Toggle\" again to restart the quiz!")
                        qNum = 0
                        checkScore.resetScore()

//                        }
//                        qNum = 0

                    }
                    else {




                    //this fetches the question
                    globalAnswer = questionList[qNum - 1].getAnswers().getAnswer()   //answerList[qNum].getIsTrue()
                    basicQuestionView.setText(questionList[qNum - 1].getQuestion())
                    fullImgStr = urlIMAGE2 + questionList[qNum - 1].getImageName()
                        if (qNum - 1 == 0) {
                            scoreView.setText("Score")
                        }


                    // This displays an image that should be associated a question
                    val vidQueue = Volley.newRequestQueue(this)
                    val imageRequest = ImageRequest(
                        fullImgStr,
                        { response: Bitmap ->
                            // Display the first 500 characters of the response string.
                            imageView.setImageBitmap(response)
                        },
                        0, 0,
                        ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,

                        { error -> basicQuestionView.text = "Error: ${error}" })

                    vidQueue.add(imageRequest)
                }
                },
                { error ->  basicQuestionView.text = "Error: ${error}" })

            queue.add(jsonArrayRequest)
            fullImgStr = ""
            qNum += 1
            count += 1

        }

        trueButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(v: View?) {
                var localAns: String = "true"
                if (localAns == globalAnswer) {
                    score = checkScore.inc()
                    scoreView.setText("Current score: $score")
                    Toast.makeText(baseContext, "Correct! Score = $score", Toast.LENGTH_SHORT).show()
                }
                else {
                    score = checkScore.dec()
                    scoreView.setText("Current score: $score")
                    Toast.makeText(baseContext, "Incorrect! Score = $score", Toast.LENGTH_SHORT).show()
                }
            }


        })

        falseButton.setOnClickListener (object: View.OnClickListener {
            override fun onClick(v: View?) {
                var localAns: String = "false"
                if (localAns == globalAnswer) {
                    score = checkScore.inc()
                    scoreView.setText("Current score: $score")
                    Toast.makeText(baseContext, "Correct! Score = $score", Toast.LENGTH_SHORT).show()
                }
                else {
                    score = checkScore.dec()
                    scoreView.setText("Current score: $score")
                    Toast.makeText(baseContext, "Incorrect! Score = $score", Toast.LENGTH_SHORT).show()
                }
            }


        })

//        getImageButton.setOnClickListener {
//
//            val queue = Volley.newRequestQueue(this)
//            val imageRequest = ImageRequest(
//                urlIMAGE2,
//                { response: Bitmap ->
//                    // Display the first 500 characters of the response string.
//                    imageView.setImageBitmap(response)
//                },
//                0,0,
//                ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,
//
//                { error ->  basicQuestionView.text = "Error: ${error}" })
//
//            queue.add(imageRequest)
//        }


    }

}