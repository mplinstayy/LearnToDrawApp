package com.example.learndrawing.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learndrawing.ui.theme.MyWhite
import com.example.learndrawing.R
import com.example.learndrawing.classes.Question
import com.example.learndrawing.classes.QuestionClass
import com.example.learndrawing.ui.theme.Charcoal
import com.example.learndrawing.ui.theme.handjetFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QuestionScreen(
    navigator: DestinationsNavigator
) {
    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ){

        Questions(q = Question(), navigator = navigator)
    }
}


@Composable
fun Questions(
    q: Question,
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    var questionId by rememberSaveable { mutableStateOf(q.questionList[q.currentIndex].question) }
    var imageId by rememberSaveable { mutableStateOf(q.questionList[q.currentIndex].image) }
    var correctAnswerId by rememberSaveable { mutableStateOf(q.questionList[q.currentIndex].correctAnswer) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.question ) + " " + (q.currentIndex + 1),
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp,
                color = Charcoal,
                letterSpacing = 2.sp,
            )
        }
        Text(
            text = stringResource(id = questionId),
            fontFamily = handjetFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            color = Charcoal,
            modifier = Modifier
                .padding(16.dp)
        )
        ImageElementQuestion(image = painterResource(id = imageId))

        Row(horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            TextButton(onClick = {
                q.checkAnswer(true, context)
            }) {
                Text(text = stringResource(id = R.string.true_button),
                    fontFamily = handjetFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Charcoal,
                    textDecoration = TextDecoration.Underline
                )
            }
            TextButton(onClick = {
                q.checkAnswer(false, context)
            }) {
                Text(text = stringResource(id = R.string.false_button),
                    fontFamily = handjetFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Charcoal,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
        Row(horizontalArrangement = Arrangement.Center){
            TextButton(onClick = {
                q.currentIndex = (q.currentIndex + 1) % q.questionList.size
                questionId = q.questionList[q.currentIndex].question
                imageId = q.questionList[q.currentIndex].image
                correctAnswerId = q.questionList[q.currentIndex].correctAnswer
            }) {
                Text(text = if (q.currentIndex != q.questionList.size - 1) stringResource( id = R.string.next_button)
                else stringResource(id = R.string.start_again),
                    fontFamily = handjetFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Charcoal,
                    textDecoration = TextDecoration.Underline
                )
            }
            if (q.currentIndex == q.questionList.size - 1){
                TextButton(onClick = { navigator.popBackStack() }) {
                    Text(text = stringResource(id = R.string.exit),
                        fontFamily = handjetFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = Charcoal,
                        textDecoration = TextDecoration.Underline
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        InfoAnimatedBlock(title = stringResource(id = R.string.answer),
            text = stringResource(id = correctAnswerId)
        )
    }
}

@Composable
fun ImageElementQuestion(
    image: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = image, contentDescription = "image",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(vertical = 32.dp, horizontal = 8.dp)
        )
    }
}