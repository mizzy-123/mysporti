package com.manifestasi.mysporty.util

import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.model.DataExercise

object Excersise {
    val getData: List<DataExercise> = listOf(
        DataExercise(
            name = "Jumping Jack",
            description = "Jumping jack test",
            link_youtube = "httpasdf",
            image = R.drawable.jumping_jack,
            default_repetisi = 12,
            tutorialList = emptyList()
        ),
        DataExercise(
            name = "Squat",
            description = "sasdfdasf",
            link_youtube = "asdfasdf",
            image = R.drawable.squat,
            default_repetisi = 20,
            tutorialList = emptyList()
        ),
        DataExercise(
            name = "Pushup",
            description = "sasdfdasf",
            link_youtube = "asdfasdf",
            image = R.drawable.pushup,
            default_repetisi = 16,
            tutorialList = emptyList()
        ),
    )
}