package com.example.learndrawing.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.learndrawing.R

val handjetFamily = FontFamily(
    Font(R.font.aa_magnum, FontWeight.Black),
    Font(R.font.ponter, FontWeight.Bold),
    Font(R.font.handjet_circle_double_light, FontWeight.Light),
    Font(R.font.handjet_circle_double_semibold, FontWeight.SemiBold),
    Font(R.font.handjet_circle_double_thin, FontWeight.Thin),
    Font(R.font.handjet_circle_extralight, FontWeight.ExtraLight),
    Font(R.font.handjet_flower_double_medium, FontWeight.Medium),
    Font(R.font.handjet_heart_double_extrabold, FontWeight.ExtraBold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)
