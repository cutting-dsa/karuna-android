package com.karuna.pages.data.entities

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class Listing(
    @SerializedName("listingname") var listingname: String = "",
    @SerializedName("active") var active: Int = 0,
    @SerializedName("address") var address: String = "",
    @SerializedName("bannerUrl") var bannerUrl: String = "",
    @SerializedName("approved") var approved: Int = 0,
    @SerializedName("listinguser") var listinguser: User,
    @SerializedName("category") var category: Category,
    @SerializedName("averageRating") var averageRating: Int
)