package com.manifestasi.mysporty.data.model

data class DataExercise(
    val id: String,
    val name: String,
    val start: String,
    val start_state: String,
    val description: String,
    val link_youtube: String,
    val image: Int,
    val default_repetisi: Int,
    val tutorialList: List<Tutorial>
)

data class Tutorial(
    val name: String,
    val description: String
)
