package com.example.learndrawing.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.learndrawing.R
import com.example.learndrawing.classes.StoreDataPhotos
import com.example.learndrawing.classes_perspective.StoreDataPerspective
import com.example.learndrawing.screens.destinations.*
import com.example.learndrawing.ui.theme.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun MainScreen(
    navigator :DestinationsNavigator
){
    Column (verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ){
        TopNote()
        ResultsButton(navigator = navigator)
        CourseButtons(navigator = navigator)
        InfoButton(navigator = navigator)
    }
}

@Composable
fun TopNote() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
    ) {
        Image(painter = painterResource(id = R.drawable.note_bg),
            contentDescription = "note",
            modifier = Modifier
                .width(360.dp)
                .height(200.dp)
        )
        Image(painter = painterResource(id = R.drawable.title_outline),
            contentDescription = "title outline",
            modifier = Modifier
                .padding(26.dp, 30.dp)
                .width(238.dp)
                .height(50.dp)
        )
        Text(text = stringResource(id = R.string.main_title),
            fontFamily = handjetFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            color = Charcoal,
            letterSpacing = 2.sp,
            modifier = Modifier
                .padding(30.dp, 30.dp)
        )
        Text(text = stringResource(id = R.string.main_description),
            fontFamily = FontFamily.Default,
            fontSize = 20.sp,
            color = Charcoal,
            letterSpacing = 1.sp,
            modifier = Modifier
                .padding(start = 18.dp, top = 80.dp)
        )
    }
}

@Composable
fun ResultsButton(
    navigator: DestinationsNavigator
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(LightPurple)
            .fillMaxWidth()
            .clickable { navigator.navigate(ResultsScreenDestination)}
    ) {        
        Row{
            Text(text = stringResource(id = R.string.my_results),
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
fun CourseButtons(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val dataStore = StoreDataPhotos(context)
    val scope = rememberCoroutineScope()

    Text(text = stringResource(id = R.string.steps),
        fontSize = 24.sp,
        color = Charcoal,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
    )
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clickable { navigator.navigate(PerspectiveViewDestination)
                    scope.launch {
                        dataStore.deleteData()
                    }
                }
        ) {
            Image(painter = painterResource(id = R.drawable.perspective_bg),
                contentDescription = "perspective button",
                modifier = Modifier
                    .fillMaxSize()
            )
            Image(painter = painterResource(id = R.drawable.perspective_outline),
                contentDescription = "perspective outline",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(140.dp)
                    .height(21.dp)
                )
            Text(text = stringResource(id = R.string.perspective),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp
            )
        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clickable { navigator.navigate(LightShadowViewDestination) }

        ) {
            Image(painter = painterResource(id = R.drawable.light_shadow_bg),
                contentDescription = "light shadow button",
                modifier = Modifier
                    .fillMaxSize()
            )
            Image(painter = painterResource(id = R.drawable.light_shadow_outline),
                contentDescription = "light shadow outline",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(140.dp)
                    .height(21.dp)
            )
            Text(text = stringResource(id = R.string.light_shadow),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp
            )
        }
    }
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()) {

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clickable { navigator.navigate(LinesViewDestination)}

        ) {
            Image(painter = painterResource(id = R.drawable.lines_bg),
                contentDescription = "lines button",
                modifier = Modifier
                    .fillMaxSize()
            )
            Image(painter = painterResource(id = R.drawable.lines_outline),
                contentDescription = "lines outline",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(140.dp)
                    .height(21.dp)
            )
            Text(text = stringResource(id = R.string.lines),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp
            )
        }

        Box(contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(8.dp)
                .height(155.dp)
                .width(165.dp)
                .clickable { navigator.navigate(VolumeViewDestination) }

        ) {
            Image(painter = painterResource(id = R.drawable.volume_bg),
                contentDescription = "volume button",
                modifier = Modifier
                    .fillMaxSize()
            )
            Image(painter = painterResource(id = R.drawable.volume_outline),
                contentDescription = "volume outline",
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(140.dp)
                    .height(21.dp)
            )
            Text(text = stringResource(id = R.string.volume),
                textAlign = TextAlign.Center,
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                color = Charcoal,
                fontSize = 26.sp
            )
        }
    }
}

@Composable
fun InfoButton(
    navigator: DestinationsNavigator
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .padding(8.dp)
            .height(56.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(LightPurple)
            .fillMaxWidth()
            .clickable { navigator.navigate(InfoScreenDestination) }
    ) {
        Row {
            Text(text = stringResource(id = R.string.app_info),
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Charcoal,
                modifier = Modifier
                    .padding(8.dp)
                    .wrapContentWidth()
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview(){
    MainScreen(EmptyDestinationsNavigator)
}
