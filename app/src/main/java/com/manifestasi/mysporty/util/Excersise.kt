package com.manifestasi.mysporty.util

import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.model.DataExercise

object Excersise {
    val getData: List<DataExercise> = listOf(
        DataExercise(
            id = "jumping_jack",
            name = "Jumping Jack",
            start = "jumping_jack_start",
            start_state = "start",
            description = "Jumping jack test",
            link_youtube = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/CWpmIW6l-YA?si=URYW_jO7DKJDQnKv\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            image = R.drawable.jumping_jack,
            default_repetisi = 12,
            tutorialList = emptyList()
        ),
        DataExercise(
            id = "squat",
            name = "Squat",
            start = "squat_up",
            start_state = "up",
            description = "sasdfdasf",
            link_youtube = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/YaXPRqUwItQ?si=FeLXr1BDksJbO1pS\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            image = R.drawable.squat,
            default_repetisi = 20,
            tutorialList = emptyList()
        ),
        DataExercise(
            id = "pushup",
            name = "Pushup",
            start = "pushup_up",
            start_state = "up",
            description = "sasdfdasf",
            link_youtube = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/mECzqUIDWfU?si=FUS_OmoLct7pHQUl\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>",
            image = R.drawable.pushup,
            default_repetisi = 16,
            tutorialList = emptyList()
        ),
    )
}