package com.manifestasi.mysporty.data.model

data class DataExercise(
    val name: String,
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
