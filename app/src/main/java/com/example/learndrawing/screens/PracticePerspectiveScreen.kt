package com.example.learndrawing.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import coil.compose.rememberAsyncImagePainter
import com.example.learndrawing.R
import com.example.learndrawing.classes.PhotoPickerData
import com.example.learndrawing.classes.StoreDataPhotos
import com.example.learndrawing.classes_light.StoreDataLight
import com.example.learndrawing.screens.destinations.QuestionScreenDestination
import com.example.learndrawing.ui.theme.Charcoal
import com.example.learndrawing.ui.theme.LightPurple
import com.example.learndrawing.ui.theme.MyWhite
import com.example.learndrawing.ui.theme.handjetFamily
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@Destination
@Composable
fun PracticePerspective(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .background(MyWhite)
            .verticalScroll(rememberScrollState())
    ){
        Title(text = R.string.practice)
        TitleElement(text = R.string.perspective)
        TextElement(text = R.string.practice_perspective_1)
        TextElement(text = R.string.practice_perspective_2)
        ImageElement(image = painterResource(id = R.drawable.practice_perspective))
        ImageElement(image = painterResource(id = R.drawable.practice_perspective_2))

        //PhotoPicker()
        TestsButton(navigator = navigator, destination = QuestionScreenDestination.route)
    }
}

@Composable
fun PhotoPicker() {

    val context = LocalContext.current
    val dataStore = StoreDataPhotos(context)
    val scope = rememberCoroutineScope()

    var selectImages by remember { mutableStateOf(listOf<Uri>()) } //создать класс для хранения?
    //var selectImages by remember { mutableStateOf(PhotoPickerData.photoPickerList) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetMultipleContents()){
            selectImages = it
        }

    /*if(dataStore.isKeyStored(stringPreferencesKey("store_data_photos_perspective")).collectAsState(initial = false).value) {
        dataStore.getDataPerspective.collectAsState(initial = mutableListOf()).value.let {
            selectImages.clear()
            it.forEach {
                selectImages.add(it)
                Log.d("PHPTO", selectImages.size.toString())
            }
        }
        if (selectImages.size != 0){
            images = selectImages
        }
    }
    selectImages = images*/


    Log.d("firstLogD", selectImages.size.toString())
    Column(
        Modifier
            .fillMaxWidth()
            .height(180.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        TextButton(onClick = {
            galleryLauncher.launch("image/*", )

        },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = "загрузить фото",
                fontFamily = handjetFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp,
                color = Charcoal,
                textDecoration = TextDecoration.Underline
            )

            Log.d("secondLogD", selectImages.size.toString())
            LazyVerticalGrid(GridCells.Fixed(1)){
                items(selectImages){uri ->
                    Image(painter = rememberAsyncImagePainter(uri),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(8.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .width(100.dp)
                            .height(100.dp)
                            .clickable { }
                    )
                }
                /*scope.launch {
                    dataStore.saveDataPerspective(selectImages)
                    Log.d("thirdLogD", selectImages.size.toString())
                }*/
            }
        }
    }
}

@Composable
fun TestsButton(
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
            Text(text = stringResource(id = R.string.test_button),
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