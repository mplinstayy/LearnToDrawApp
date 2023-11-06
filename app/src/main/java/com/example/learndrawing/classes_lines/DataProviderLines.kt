package com.example.learndrawing.classes_lines

import com.example.learndrawing.R
import com.example.learndrawing.screens.destinations.LinesFirstStepDestination
import com.example.learndrawing.screens.destinations.LinesSecondStepDestination
import com.example.learndrawing.screens.destinations.LinesThirdStepDestination
import com.example.learndrawing.screens.destinations.PerspectiveFirstStepDestination

object DataProviderLines {
    val linesList = listOf(
        LinesClass(
            id = 0,
            title = R.string.step_1,
            text = "Проведение линий",
            image = R.drawable.step_1_lines,
            isFinished = LinesFinishedClass.linesFinishedList[0],
            destination = LinesFirstStepDestination.route
        ),
        LinesClass(
            id = 1,
            title = R.string.step_2,
            text = "Пространственные свойства линии",
            image = R.drawable.step_2_lines,
            isFinished = LinesFinishedClass.linesFinishedList[1],
            destination = LinesSecondStepDestination.route
        ),
        LinesClass(
            id = 2,
            title = R.string.step_3,
            text = "Техника штриха",
            image = R.drawable.step_3_lines,
            isFinished = LinesFinishedClass.linesFinishedList[2],
            destination = LinesThirdStepDestination.route
        )
    )
}