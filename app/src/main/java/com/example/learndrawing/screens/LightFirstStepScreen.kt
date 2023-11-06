package com.example.learndrawing.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learndrawing.R
import com.example.learndrawing.classes_light.DataProviderLight
import com.example.learndrawing.classes_light.LightFinishedClass
import com.example.learndrawing.classes_light.StoreDataLight
import com.example.learndrawing.classes_perspective.DataProviderPerspective
import com.example.learndrawing.classes_perspective.PerspectiveFinishedClass
import com.example.learndrawing.classes_perspective.StoreDataPerspective
import com.example.learndrawing.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun LightShadowFirstStep(
    navigator: DestinationsNavigator
) {
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxSize()
        .background(MyWhite)
        .verticalScroll(rememberScrollState())
    ){
        Title(R.string.step_1)
        TitleElement(text = R.string.light_shadow)
        NoteElement(text = R.string.light_shadow_1_paragraph,
            image = painterResource(id = R.drawable.note_vector),
            tint = LightGreen
        )
        TextElement(text = R.string.light_shadow_2_paragraph)
        ParagraphTitleElement(text = R.string.light_shadow_3_title)
        TextElement(text = R.string.light_shadow_3_paragraph)
        ParagraphTitleElement(text = R.string.light_shadow_4_title)
        TextElement(text = R.string.light_shadow_4_paragraph)
        TextElement(text = R.string.light_shadow_5_paragraph)

        FinishButtonLightShadow(navigator = navigator, id = 0)
    }
}

@Composable
fun FinishButtonLightShadow(
    navigator: DestinationsNavigator,
    id: Int
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreDataLight(context)

    val steps = remember { DataProviderLight.lightShadowList }
    val stepIsFinished = remember { LightFinishedClass.lightFinishedList }
    var isClicked by remember { mutableStateOf(steps[id].isFinished) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(onClick = {
            isClicked = !steps[id].isFinished
            stepIsFinished[id] = isClicked

            scope.launch {
                dataStore.saveData(stepIsFinished)
            }

            navigator.popBackStack()
        },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = LightPurple, contentColor = Charcoal
            ),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 16.dp)
                .height(56.dp)
                .fillMaxWidth()
        ) {
            Text(text = if (!steps[id].isFinished) stringResource(id = R.string.finish_step)
            else stringResource(id = R.string.finish_step_cancel),
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Charcoal,
                modifier = Modifier
                    .wrapContentWidth()
            )
        }
    }
}