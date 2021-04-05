package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Category(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "")