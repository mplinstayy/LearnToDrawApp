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
fun LightShadowSecStep(
    navigator: DestinationsNavigator
){
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxSize()
        .background(MyWhite)
        .verticalScroll(rememberScrollState())
    ){
        Title(R.string.step_2)
        TitleElement(text = R.string.light_shadow_sec_title)
        ImageElement(image = painterResource(id = R.drawable.light_shadow_pic))
        ParagraphTitleElement(text = R.string.light_shadow_sec_1_title)
        TextElement(text = R.string.light_shadow_sec_1_paragraph)
        ParagraphTitleElement(text = R.string.light_shadow_sec_2_title)
        TextElement(text = R.string.light_shadow_sec_2_paragraph)
        ParagraphTitleElement(text = R.string.light_shadow_sec_3_title)
        TextElement(text = R.string.light_shadow_sec_3_paragraph)
        ParagraphTitleElement(text = R.string.light_shadow_sec_4_title)
        TextElement(text = R.string.light_shadow_sec_4_paragraph)
        ParagraphTitleElement(text = R.string.light_shadow_sec_5_title)
        TextElement(text = R.string.light_shadow_sec_5_paragraph)
        ParagraphTitleElement(text = R.string.light_shadow_sec_6_title)
        TextElement(text = R.string.light_shadow_sec_6_paragraph)

        FinishButtonLightShadow(navigator = navigator, id = 1)
    }
}