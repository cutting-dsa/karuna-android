package com.karuna.pages.data.entities

import com.google.gson.annotations.SerializedName

data class Question(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("name") var name: String = "",
    @SerializedName("status") var status: Boolean = false,
    @SerializedName("category") var category: Category,
    @SerializedName("created_at") var created_at: String = "",
)