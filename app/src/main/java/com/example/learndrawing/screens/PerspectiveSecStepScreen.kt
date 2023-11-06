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
import com.example.learndrawing.ui.theme.LightPink
import com.example.learndrawing.ui.theme.MyWhite
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PerspectiveSecStep(
    navigator: DestinationsNavigator
) {
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxSize()
        .background(MyWhite)
        .verticalScroll(rememberScrollState())
    ){
        Title(text = R.string.step_2)
        TitleElement(text = R.string.air_persp_title)
        NoteElement(text = R.string.air_persp_1_paragraph,
            image = painterResource(id = R.drawable.note_vector),
            tint = LightPink)
        ParagraphTitleElement(text = R.string.air_persp_2_title)
        TextElement(text = R.string.air_persp_2_paragraph)
        ImageElement(image = painterResource(id = R.drawable.air_persp_pic))
        ParagraphTitleElement(text = R.string.air_persp_3_title)
        TextElement(text = R.string.air_persp_3_paragraph)

        FinishButtonPerspective(navigator = navigator, id = 1)
    }
}