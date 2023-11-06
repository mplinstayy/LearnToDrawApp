package com.example.learndrawing.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.learndrawing.R
import com.example.learndrawing.ui.theme.LightYellow
import com.example.learndrawing.ui.theme.MyWhite
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun LinesThirdStep(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ) {
        Title(R.string.step_3)
        TitleElement(text = R.string.lines_third_title)
        NoteElement(text = R.string.lines_third_1_paragraph,
            image = painterResource(id = R.drawable.note_vector),
            tint = LightYellow
        )
        TextElement(text = R.string.lines_third_2_paragraph)
        ImageElement(image = painterResource(id = R.drawable.lines_pic3))
        TextElement(text = R.string.lines_third_3_paragraph)
        TextElement(text = R.string.lines_third_4_paragraph)
        TextElement(text = R.string.lines_third_5_paragraph)
        ImageElement(image = painterResource(id = R.drawable.lines_pic4))
        TextElement(text = R.string.lines_third_6_paragraph)


        FinishButtonLines(navigator = navigator, id = 2)
    }
}