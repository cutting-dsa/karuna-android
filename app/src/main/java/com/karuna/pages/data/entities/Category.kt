package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Category(
    @SerializedName("id") var id: String = "",
    @SerializedName("name") var name: String = "")