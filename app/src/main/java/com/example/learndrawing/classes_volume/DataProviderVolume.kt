package com.example.learndrawing.classes_volume

import com.example.learndrawing.R
import com.example.learndrawing.screens.destinations.PerspectiveFirstStepDestination
import com.example.learndrawing.screens.destinations.VolumeFirstStepDestination
import com.example.learndrawing.screens.destinations.VolumeSecondStepDestination
import com.example.learndrawing.screens.destinations.VolumeThirdStepDestination

object DataProviderVolume {
    val volumeList = listOf(
        VolumeClass(
            id = 0,
            title = R.string.step_1,
            text = "Построение формы линией",
            image = R.drawable.step_1_volume,
            isFinished = VolumeFinishedClass.volumeFinishedList[0],
            destination = VolumeFirstStepDestination.route
        ),
        VolumeClass(
            id = 1,
            title = R.string.step_2,
            text = "Построение объема тоном",
            image = R.drawable.step_2_volume,
            isFinished = VolumeFinishedClass.volumeFinishedList[1],
            destination = VolumeSecondStepDestination.route
        ),
        VolumeClass(
            id = 2,
            title = R.string.step_3,
            text = "Понятие 'Большая форма'",
            image = R.drawable.step_3_volume,
            isFinished = VolumeFinishedClass.volumeFinishedList[2],
            destination = VolumeThirdStepDestination.route
        )
    )
}