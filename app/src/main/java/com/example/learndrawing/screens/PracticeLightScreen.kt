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
import com.example.learndrawing.screens.destinations.QuestionScreenDestination
import com.example.learndrawing.ui.theme.MyWhite
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PracticeLightShadow(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ){
        Title(text = R.string.practice)
        TitleElement(text = R.string.light_shadow)
        TextElement(text = R.string.practice_light_shadow)
        TextElement(text = R.string.practice_light_shadow_2)
        TextElement(text = R.string.practice_light_shadow_3)
        ImageElement(image = painterResource(id = R.drawable.practice_light_shadow))
        ImageElement(image = painterResource(id = R.drawable.practice_light_shadow_2))

        //PhotoPicker()
        TestsButton(navigator = navigator, destination = QuestionScreenDestination.route)
    }
}