package com.example.learndrawing.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.learndrawing.R
import com.example.learndrawing.classes_light.DataProviderLight
import com.example.learndrawing.classes_light.LightFinishedClass
import com.example.learndrawing.classes_light.LightShadowClass
import com.example.learndrawing.classes_lines.DataProviderLines
import com.example.learndrawing.classes_lines.LinesClass
import com.example.learndrawing.classes_lines.LinesFinishedClass
import com.example.learndrawing.classes_lines.StoreDataLines
import com.example.learndrawing.classes_perspective.StoreDataPerspective
import com.example.learndrawing.screens.destinations.MainScreenDestination
import com.example.learndrawing.screens.destinations.PracticeLinesDestination
import com.example.learndrawing.ui.theme.Charcoal
import com.example.learndrawing.ui.theme.MyWhite
import com.example.learndrawing.ui.theme.handjetFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun LinesView(
    navigator: DestinationsNavigator
) {
    Column (verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
    ){
        Title(text = R.string.lines)
        LinesSteps(navigator = navigator)
    }
    Column(verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        PracticeButton(navigator = navigator, destination = PracticeLinesDestination.route)
    }
}

@Composable
fun LinesSteps(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val dataStore = StoreDataLines(context)

    val steps = remember { DataProviderLines.linesList }
    val finishedSteps = remember { LinesFinishedClass.linesFinishedList }

    if(dataStore.isKeyStored(stringPreferencesKey("store_data_lines")).collectAsState(initial = false).value) {
        dataStore.getData.collectAsState(initial = mutableListOf()).value.let {
            finishedSteps.clear()
            it.forEach {
                finishedSteps.add(it)
            }
        }
        if (finishedSteps.size != 0){
            steps.forEach{
                it.isFinished = finishedSteps[it.id]
            }
        }
    }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ){
        items(
            items = steps,
            itemContent = {
                LinesStepButtons(step = it, navigator = navigator)
            }
        )
    }
}

@Composable
fun LinesStepButtons(
    step: LinesClass,
    navigator: DestinationsNavigator
) {
    Box(contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .height(140.dp)
            .fillMaxWidth()
            .clickable { navigator.navigate(step.destination) }
    ){
        Image(
            painter = painterResource(id = step.image), contentDescription = "step",
            modifier = Modifier
                .height(140.dp)
                .width(400.dp)
        )
        Text(
            text = if (!step.isFinished) stringResource(id = step.title)
            else stringResource(id = step.title) + " "
                    + stringResource(id = R.string.is_finished),
            fontFamily = handjetFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 26.sp,
            color = Charcoal,
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = step.text,
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            textDecoration = TextDecoration.Underline,
            color = Charcoal,
            modifier = Modifier.padding(top = 58.dp, start = 14.dp)
        )
    }
}