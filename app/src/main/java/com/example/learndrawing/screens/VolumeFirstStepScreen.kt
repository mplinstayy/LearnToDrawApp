package com.example.learndrawing.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.learndrawing.R
import com.example.learndrawing.classes_volume.DataProviderVolume
import com.example.learndrawing.classes_volume.StoreDataVolume
import com.example.learndrawing.classes_volume.VolumeFinishedClass
import com.example.learndrawing.ui.theme.*
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@Destination
@Composable
fun VolumeFirstStep(
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
        TitleElement(text = R.string.volume_title)

        TextElement(text = R.string.volume_1_paragraph)
        TextElement(text = R.string.volume_2_paragraph)
        TextElement(text = R.string.volume_3_paragraph)
        ImageElement(image = painterResource(id = R.drawable.volume_pic))
        TextElement(text = R.string.volume_4_paragraph)
        NoteElement(text = R.string.volume_5_paragraph, 
            image = painterResource(id = R.drawable.note_vector), 
            tint = LightBlue
        )
        NoteElement(text = R.string.volume_6_paragraph,
            image = painterResource(id = R.drawable.note_vector),
            tint = LightBlue
        )
        TextElement(text = R.string.volume_7_paragraph)

        FinishButtonVolume(navigator = navigator, id = 0)
    }
}

@Composable
fun YoutubeVideoPlayer(
    videoId: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    AndroidView(factory = {
        val view = YouTubePlayerView(context)
        view.addYouTubePlayerListener(
            object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    youTubePlayer.cueVideo(videoId, 0f)
                }
            }
        )
        view
    })
}


@Composable
fun FinishButtonVolume(
    navigator: DestinationsNavigator,
    id: Int
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = StoreDataVolume(context)

    val steps = remember { DataProviderVolume.volumeList }
    val stepIsFinished = remember { VolumeFinishedClass.volumeFinishedList }
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