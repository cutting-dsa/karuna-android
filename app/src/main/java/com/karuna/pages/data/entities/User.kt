package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class User(
    @SerializedName("name") var name: String = "",
    @SerializedName("email") var email: String = ""
)
