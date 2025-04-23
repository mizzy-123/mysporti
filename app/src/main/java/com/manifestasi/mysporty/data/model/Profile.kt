package com.manifestasi.mysporty.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Profile(

    @SerialName("user_id")
    val user_id: String,

    @SerialName("first_name")
    val first_name: String,

    @SerialName("last_name")
    val last_name: String,

    @SerialName("height")
    val height: Int? = null,

    @SerialName("weight")
    val weight: Int? = null,

    @SerialName("age")
    val age: Int? = null
)
