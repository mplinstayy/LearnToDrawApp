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
import com.example.learndrawing.ui.theme.MyWhite
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun VolumeSecondStep(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ) {
        Title(R.string.step_2)
        TitleElement(text = R.string.volume_sec_title)
        TextElement(text = R.string.volume_sec_1_paragraph)    
        TextElement(text = R.string.volume_sec_2_paragraph)    
        TextElement(text = R.string.volume_sec_3_paragraph)    
        ImageElement(image = painterResource(id = R.drawable.volume_pic2))
        TextElement(text = R.string.volume_sec_4_paragraph)    

        FinishButtonVolume(navigator = navigator, id = 1)
    }
}