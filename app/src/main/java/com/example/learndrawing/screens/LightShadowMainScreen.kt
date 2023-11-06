package com.example.learndrawing.screens

import android.util.Log
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
import com.example.learndrawing.classes_light.StoreDataLight
import com.example.learndrawing.classes_perspective.StoreDataPerspective
import com.example.learndrawing.screens.destinations.MainScreenDestination
import com.example.learndrawing.screens.destinations.PracticeLightShadowDestination
import com.example.learndrawing.ui.theme.Charcoal
import com.example.learndrawing.ui.theme.MyWhite
import com.example.learndrawing.ui.theme.handjetFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
@Destination
fun LightShadowView(
    navigator: DestinationsNavigator
) {
    Column (verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
    ){
        Title(text = R.string.light_shadow)
        LightSteps(navigator = navigator)
    }
    Column(verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ){
        PracticeButton(navigator = navigator, destination = PracticeLightShadowDestination.route)
    }
}

@Composable
fun LightSteps(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val dataStore = StoreDataLight(context)

    val steps = remember { DataProviderLight.lightShadowList }
    val finishedSteps = remember { LightFinishedClass.lightFinishedList }

    if(dataStore.isKeyStored(stringPreferencesKey("store_data_light")).collectAsState(initial = false).value) {
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
                LightStepButtons(step = it, navigator = navigator)
            }
        )
    }
}

@Composable
fun LightStepButtons(
    step: LightShadowClass,
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