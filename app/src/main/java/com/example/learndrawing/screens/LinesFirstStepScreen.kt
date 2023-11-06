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
import com.example.learndrawing.classes_lines.DataProviderLines
import com.example.learndrawing.classes_lines.LinesFinishedClass
import com.example.learndrawing.classes_lines.StoreDataLines
import com.example.learndrawing.ui.theme.Charcoal
import com.example.learndrawing.ui.theme.LightPurple
import com.example.learndrawing.ui.theme.MyWhite
import com.example.learndrawing.ui.theme.handjetFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun LinesFirstStep(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ) {
        Title(R.string.step_1)
        TitleElement(text = R.string.lines_1_title)
        TextElement(text = R.string.lines_1_paragraph)
        TextElement(text = R.string.lines_2_paragraph)
        ImageElement(image = painterResource(id = R.drawable.lines_pic))
        TextElement(text = R.string.lines_3_paragraph)

        FinishButtonLines(navigator = navigator, id = 0)
    }
}

@Composable
fun FinishButtonLines(
    navigator: DestinationsNavigator,
    id: Int
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreDataLines(context)

    val steps = remember { DataProviderLines.linesList}
    val stepIsFinished = remember { LinesFinishedClass.linesFinishedList }
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