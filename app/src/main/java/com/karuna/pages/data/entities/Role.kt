package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Role(
    @SerializedName("id") var id: String = "",
    @SerializedName("answer") var name: String = "",
)