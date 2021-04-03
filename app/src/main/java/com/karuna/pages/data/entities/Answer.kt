package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Answer(
    @SerializedName("id") var id: String = "",
    @SerializedName("answer") var name: String = "",
    @SerializedName("user") var user: User,
    @SerializedName("question") var question: Question,
    @SerializedName("created_at") var created_at: String = "",
)

/*
{
  "answer": "string",
  "created_at": "2021-04-03",
  "id": 0,
  "question": {
    "category": {
      "active": 0,
      "id": 0,
      "name": "string"
    },
    "created_at": "2021-04-03T22:23:39.240Z",
    "id": 0,
    "name": "string",
    "status": true
  },
  "user": {
    "enabled": 0,
    "firstName": "string",
    "id": 0,
    "lastName": "string",
    "password": "string",
    "roles": [
      {
        "id": 0,
        "name": "string"
      }
    ],
    "username": "string"
  }
}
 */