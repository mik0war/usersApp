package ru.mik0war.flaskapp

import com.google.gson.annotations.SerializedName

data class UserData(
    val login: String,
    val password: String,
    @SerializedName("image_url")
    val link: String
)