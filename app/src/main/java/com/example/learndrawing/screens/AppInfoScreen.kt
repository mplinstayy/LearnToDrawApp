package com.example.learndrawing.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learndrawing.R
import com.example.learndrawing.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun InfoScreen() {
    Column (verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ){
        Title(text = R.string.app_info)
        InfoAnimatedBlock(title = stringResource(id = R.string.app_description_title),
            text = stringResource(id = R.string.app_description)
        )
        InfoAnimatedBlock(title = stringResource(id = R.string.app_description_title_2),
            text = stringResource(id = R.string.app_description_2)
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun InfoAnimatedBlock(
    title: String,
    text: String
) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(
        targetValue = if (expandedState) 180f else 0f
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = Shapes.small,
        backgroundColor = LightPurple,
        onClick = {
            expandedState = !expandedState
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = title,
                    fontFamily = handjetFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp,
                    color = Charcoal,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(4f)
                        .padding(start = 8.dp)
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(ContentAlpha.medium)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }
            if (expandedState) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    color = Charcoal,
                )
            }
        }
    }
}