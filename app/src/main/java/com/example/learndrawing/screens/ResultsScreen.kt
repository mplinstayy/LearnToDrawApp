package com.example.learndrawing.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.learndrawing.R
import com.example.learndrawing.classes_light.LightFinishedClass
import com.example.learndrawing.classes_light.StoreDataLight
import com.example.learndrawing.classes_lines.LinesFinishedClass
import com.example.learndrawing.classes_lines.StoreDataLines
import com.example.learndrawing.classes_perspective.PerspectiveFinishedClass
import com.example.learndrawing.classes_perspective.StoreDataPerspective
import com.example.learndrawing.classes_volume.StoreDataVolume
import com.example.learndrawing.classes_volume.VolumeFinishedClass
import com.example.learndrawing.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ResultsScreen() {
    Column (verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
    ){
        Title(R.string.results)
        Results()
        ResultBlock()
    }
}

@Composable
fun Results() {
    Text(text = stringResource(id = R.string.steps_done),
        fontSize = 24.sp,
        fontFamily = handjetFamily,
        fontWeight = FontWeight.SemiBold,
        color = Charcoal,
        modifier = Modifier
            .padding(horizontal = 12.dp, vertical = 8.dp)
    )
}

@Composable
fun ResultBlock() {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
    ) {
        Box( contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(LightPink)
        ) {
            Text(text = stringResource(id = R.string.perspective),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                text =  perspectiveResult().toString() + "/" + PerspectiveFinishedClass.perspectiveFinishedList.size,
                fontSize = 20.sp,
                color = Charcoal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 30.dp)
                )
        }
        Box( contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(LightGreen)
        ) {
            Text(text = stringResource(id = R.string.light_shadow),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                text =  lightShadowResult().toString() + "/" + LightFinishedClass.lightFinishedList.size,
                fontSize = 20.sp,
                color = Charcoal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 30.dp)
            )
        }
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box( contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(LightYellow)
        ) {
            Text(text = stringResource(id = R.string.lines),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                text =  linesShadowResult().toString() + "/" + LinesFinishedClass.linesFinishedList.size,
                fontSize = 20.sp,
                color = Charcoal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 30.dp)
            )
        }
        Box( contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clip(RoundedCornerShape(5.dp))
                .background(LightBlue)
        ) {
            Text(text = stringResource(id = R.string.volume),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )
            Text(
                text =  volumeShadowResult().toString() + "/" + VolumeFinishedClass.volumeFinishedList.size,
                fontSize = 20.sp,
                color = Charcoal,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 30.dp)
            )
        }
    }
}


@Composable
fun perspectiveResult(): Int {
    val context = LocalContext.current
    val dataStore = StoreDataPerspective(context)
    var count = 0

    if(dataStore.isKeyStored(stringPreferencesKey("store_data_perspective")).collectAsState(initial = false).value) {
        dataStore.getData.collectAsState(initial = mutableListOf()).value.let { it ->
            it.forEach {
                if (it){
                    count++
                    //Log.d("COUNT", count.toString())
                }
            }
        }
    }
    return count
}

@Composable
fun lightShadowResult(): Int {
    val context = LocalContext.current
    val dataStore = StoreDataLight(context)
    var count = 0

    if(dataStore.isKeyStored(stringPreferencesKey("store_data_light")).collectAsState(initial = false).value) {
        dataStore.getData.collectAsState(initial = mutableListOf()).value.let { it ->
            it.forEach {
                if (it){
                    count++
                }
            }
        }
    }
    return count
}

@Composable
fun linesShadowResult(): Int {
    val context = LocalContext.current
    val dataStore = StoreDataLines(context)
    var count = 0

    if(dataStore.isKeyStored(stringPreferencesKey("store_data_lines")).collectAsState(initial = false).value) {
        dataStore.getData.collectAsState(initial = mutableListOf()).value.let { it ->
            it.forEach {
                if (it){
                    count++
                }
            }
        }
    }
    return count
}

@Composable
fun volumeShadowResult(): Int {
    val context = LocalContext.current
    val dataStore = StoreDataVolume(context)
    var count = 0

    if(dataStore.isKeyStored(stringPreferencesKey("store_data_volume")).collectAsState(initial = false).value) {
        dataStore.getData.collectAsState(initial = mutableListOf()).value.let { it ->
            it.forEach {
                if (it){
                    count++
                }
            }
        }
    }
    return count
}