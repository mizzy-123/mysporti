package com.manifestasi.mysporty.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class HistoryExcersise(
    val id_img: String,
    val name: String,
    val repetition: Int,
    val history_at: String,
)