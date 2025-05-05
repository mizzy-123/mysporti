package com.manifestasi.mysporty.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HistoryExcersise2(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("user_id")
    val user_id: String,

    @SerialName("name")
    val name: String,

    @SerialName("img_name")
    val img_name: String,

    @SerialName("repetisi")
    val repetisi: Int,

    @SerialName("created_at")
    val created_at: String? = null
)