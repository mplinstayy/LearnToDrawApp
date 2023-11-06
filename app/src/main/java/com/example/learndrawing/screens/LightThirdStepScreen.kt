package com.example.learndrawing.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun LightShadowThirdStep(
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
        TitleElement(text = R.string.light_shadow_third_title)
        YoutubeVideoPlayer(videoId = "UXIu8n1iT1Y", modifier = Modifier.height(200.dp))
        TextElement(text = R.string.light_shadow_third_1_paragraph)
        TextElement(text = R.string.light_shadow_third_2_paragraph)
        TextElement(text = R.string.light_shadow_third_3_paragraph)
        ImageElement(image = painterResource(id = R.drawable.light_shadow_pic2))
        TextElement(text = R.string.light_shadow_third_4_paragraph)
        TextElement(text = R.string.light_shadow_third_5_paragraph)
        TextElement(text = R.string.light_shadow_third_6_paragraph)


        FinishButtonLightShadow(navigator = navigator, id = 2)
    }
}