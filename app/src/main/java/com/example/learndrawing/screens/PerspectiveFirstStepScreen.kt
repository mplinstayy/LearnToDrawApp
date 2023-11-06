package com.example.learndrawing.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.compose.rememberAsyncImagePainter
import com.example.learndrawing.R
import com.example.learndrawing.classes_perspective.DataProviderPerspective
import com.example.learndrawing.classes_perspective.PerspectiveFinishedClass
import com.example.learndrawing.classes_perspective.StoreDataPerspective
import com.example.learndrawing.ui.theme.*

import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun PerspectiveFirstStep(
    navigator: DestinationsNavigator
) {
    Column(modifier = Modifier
        .padding(horizontal = 16.dp)
        .fillMaxSize()
        .background(MyWhite)
        .verticalScroll(rememberScrollState())
    ) {
        Title(R.string.step_1)
        TitleElement(text = R.string.linear_persp_title)
        YoutubeVideoPlayer(videoId = "2NePbItVKeE", modifier = Modifier.height(200.dp))
        TextElement(text = R.string.persp_1_paragraph)
        NoteElement(text = R.string.persp_2_paragraph,
            image = painterResource(id = R.drawable.note_vector),
            tint = LightPink
        )
        TextElement(text = R.string.persp_3_paragraph)
        TextElement(text = R.string.persp_4_paragraph)
        TextElement(text = R.string.persp_5_paragraph)
        ImageElement(image = painterResource(id = R.drawable.persp_linear_pic))
        TextElement(text = R.string.persp_6_paragraph)
        TextElement(text = R.string.persp_7_paragraph)
        ImageElement(image = painterResource(id = R.drawable.persp_linear_pic2))

        FinishButtonPerspective(navigator = navigator, id = 0)
    }
}

@Composable
fun TitleElement(
    text: Int
) {
    Text(text = stringResource(id = text),
        fontFamily = handjetFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        color = Charcoal,
        letterSpacing = 2.sp,
        modifier = Modifier
            .padding(16.dp)
    )
}

@Composable
fun ParagraphTitleElement(
    text: Int
) {
    Text(text = stringResource(id = text),
        fontFamily = handjetFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        color = Charcoal,
        letterSpacing = 2.sp,
        modifier = Modifier
            .padding(vertical = 6.dp, horizontal = 16.dp)
    )
}

@Composable
fun ImageElement(
    image: Painter
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = image, contentDescription = "image",
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
        )
    }
}

@Composable
fun NoteElement(
    text: Int,
    image: Painter,
    tint: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Box {
            Icon(painter = image,
                tint = tint,
                contentDescription = "note",
                modifier = Modifier
                    .width(380.dp)
                    .height(220.dp)
            )
            Text(text = stringResource(id = text),
                fontFamily = handjetFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                color = Charcoal,
                textDecoration = TextDecoration.Underline,
                letterSpacing = 2.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 40.dp, end = 20.dp)
            )
        }
    }
}

@Composable
fun TextElement(
    text: Int
) {
    Text(text = stringResource(id = text),
        fontSize = 18.sp,
        color = Charcoal,
        letterSpacing = 1.sp,
        textAlign = TextAlign.Justify,
        modifier = Modifier
            .padding(8.dp)
    )
}



@Composable
fun FinishButtonPerspective(
    navigator: DestinationsNavigator,
    id: Int
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreDataPerspective(context)

    val steps = remember { DataProviderPerspective.perspectiveList }
    val stepIsFinished = remember { PerspectiveFinishedClass.perspectiveFinishedList }
    var isClicked by remember { mutableStateOf(steps[id].isFinished)}
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