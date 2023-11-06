package com.example.learndrawing.classes_perspective

import com.example.learndrawing.R
import com.example.learndrawing.screens.destinations.PerspectiveFirstStepDestination
import com.example.learndrawing.screens.destinations.PerspectiveSecStepDestination

object DataProviderPerspective {
    val perspectiveList = listOf(
        PerspectiveClass(
            id = 0,
            title = R.string.step_1,
            text = "Линейная перспектива",
            image = R.drawable.step_1_persp,
            isFinished = PerspectiveFinishedClass.perspectiveFinishedList[0],
            destination = PerspectiveFirstStepDestination.route
        ),
        PerspectiveClass(
            id = 1,
            title = R.string.step_2,
            text = "Воздушная песпектива",
            image = R.drawable.step_2_persp,
            isFinished = PerspectiveFinishedClass.perspectiveFinishedList[1],
            destination = PerspectiveSecStepDestination.route
        )
    )
}