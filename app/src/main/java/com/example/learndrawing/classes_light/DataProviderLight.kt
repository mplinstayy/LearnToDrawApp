package com.example.learndrawing.classes_light

import com.example.learndrawing.R
import com.example.learndrawing.screens.destinations.LightShadowFirstStepDestination
import com.example.learndrawing.screens.destinations.LightShadowSecStepDestination
import com.example.learndrawing.screens.destinations.LightShadowThirdStepDestination
import com.example.learndrawing.screens.destinations.PerspectiveFirstStepDestination

object DataProviderLight {
    val lightShadowList = listOf(
        LightShadowClass(
            id = 0,
            title = R.string.step_1,
            text = "Введение в светотень",
            image = R.drawable.step_1_light,
            isFinished = LightFinishedClass.lightFinishedList[0],
            destination = LightShadowFirstStepDestination.route
        ),
        LightShadowClass(
            id = 1,
            title = R.string.step_2,
            text = "Основные понятия светотени",
            image = R.drawable.step_2_light,
            isFinished = LightFinishedClass.lightFinishedList[1],
            destination = LightShadowSecStepDestination.route
        ),
        LightShadowClass(
            id = 2,
            title = R.string.step_3,
            text = "Светотень и форма",
            image = R.drawable.step_3_light,
            isFinished = LightFinishedClass.lightFinishedList[2],
            destination = LightShadowThirdStepDestination.route
        )
    )
}