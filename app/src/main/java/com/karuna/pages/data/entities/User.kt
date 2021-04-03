package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class User(
    @SerializedName("firstName") var firstName: String = "",
    @SerializedName("lastName") var lastName: String = "",
    @SerializedName("enabled") var enabled: Int = 0,
    @SerializedName("username") var username: String = "",
    @SerializedName("roles") var roles: List<Role>,
)