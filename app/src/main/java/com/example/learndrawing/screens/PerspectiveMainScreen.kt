package com.example.learndrawing.screens

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.learndrawing.R
import com.example.learndrawing.classes_perspective.DataProviderPerspective
import com.example.learndrawing.classes_perspective.PerspectiveClass
import com.example.learndrawing.classes_perspective.PerspectiveFinishedClass
import com.example.learndrawing.classes_perspective.StoreDataPerspective
import com.example.learndrawing.screens.destinations.MainScreenDestination
import com.example.learndrawing.screens.destinations.PracticePerspectiveDestination
import com.example.learndrawing.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@Destination
@Composable
fun PerspectiveView(
    navigator: DestinationsNavigator
)
{
    Column (verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
    ){
        Title(R.string.perspective)
        PerspectiveSteps(navigator = navigator)
    }

    Column(verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        PracticeButton(navigator = navigator, destination = PracticePerspectiveDestination.route)
    }
}

@Composable
fun Title(
    text: Int
) {
    Row(modifier = Modifier.padding(16.dp)) {
        Text(text = stringResource(id = text),
            fontFamily = handjetFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            color = Charcoal,
            letterSpacing = 2.sp,
        )
    }
}


@Composable
fun PerspectiveSteps(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val dataStore = StoreDataPerspective(context)

    val steps = remember { DataProviderPerspective.perspectiveList }
    val finishedSteps = remember {PerspectiveFinishedClass.perspectiveFinishedList}

    if(dataStore.isKeyStored(stringPreferencesKey("store_data_perspective")).collectAsState(initial = false).value) {
        dataStore.getData.collectAsState(initial = mutableListOf()).value.let {
            finishedSteps.clear()
            it.forEach {
                //Log.d("Out datastore", it.toString())
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
                PerspectiveStepButtons(step = it, navigator = navigator)
            }
        )
    }
}


@Composable
fun PerspectiveStepButtons(
    step: PerspectiveClass,
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


@Composable
fun PracticeButton(
    navigator: DestinationsNavigator,
    destination: String
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(LightPurple)
            .fillMaxWidth()
            .clickable { navigator.navigate(destination) }
    ) {
        Row{
            Text(text = stringResource(id = R.string.practice),
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Charcoal,
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 20.dp)
                    .weight(4f)
            )
            Icon(painter = painterResource(id = R.drawable.arrow_icon),
                contentDescription = "arrow icon",
                tint = MyWhite,
                modifier = Modifier
                    .padding(18.dp)
                    .weight(1f)
                    .size(24.dp)
            )
        }
    }
}



@Composable
@Preview(showBackground = true)
fun PerspectiveMainScreenPreview(){
    PerspectiveView(EmptyDestinationsNavigator)
}