package com.example.learndrawing.classes

import android.content.Context
import android.widget.Toast
import com.example.learndrawing.R

class Question {
     val questionList = listOf(
        QuestionClass(R.drawable.question_pic_1 , R.string.question_1, false, R.string.question_1_answer),
        QuestionClass(R.drawable.question_pic_2 , R.string.question_2, false, R.string.question_2_answer),
        QuestionClass(R.drawable.light_shadow_pic , R.string.question_3, true, R.string.question_3_answer)
    )
     var currentIndex = 0

     fun checkAnswer(userAnswer: Boolean, context: Context){
         val correctAnswer = questionList[currentIndex].answer
         val message =
             if (userAnswer == correctAnswer) R.string.correct
         else R.string.incorrect

         Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
     }
}