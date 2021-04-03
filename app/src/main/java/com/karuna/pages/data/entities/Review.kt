package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Review(
    @SerializedName("id") var id: Int = 0,
    @SerializedName("comment") var comment: String = "",
    @SerializedName("listing") var listing: Listing,
    @SerializedName("rating") var rating: Int = 0,
    @SerializedName("reviewUser") var reviewUser: User,
    @SerializedName("status") var status: Int = 0
)

/*
{
    "comment": "string",
    "id": 0,
    "listing": {
      "active": 0,
      "address": "string",
      "approved": 0,
      "averageRating": 0,
      "bannerUrl": "string",
      "category": {
        "active": 0,
        "id": 0,
        "name": "string"
      },
      "iconUrl": "string",
      "id": 0,
      "latitude": 0,
      "listingname": "string",
      "listinguser": {
        "enabled": 0,
        "firstName": "string",
        "id": 0,
        "lastName": "string",
        "roles": [
          {
            "id": 0,
            "name": "string"
          }
        ],
        "username": "string"
      },
      "longitude": 0
    },
    "rating": 0,
    "reviewUser": {
      "enabled": 0,
      "firstName": "string",
      "id": 0,
      "lastName": "string",
      "roles": [
        {
          "id": 0,
          "name": "string"
        }
      ],
      "username": "string"
    },
    "status": 0
  }
 */