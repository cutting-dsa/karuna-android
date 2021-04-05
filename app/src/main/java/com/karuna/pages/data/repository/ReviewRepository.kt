package com.karuna.pages.data.repository

import android.content.Context
import com.karuna.pages.data.entities.Review
import com.karuna.pages.data.remote.ReviewRemoteDataSource
import com.karuna.pages.utils.PreferenceManager
import com.karuna.pages.utils.Resource

class ReviewRepository constructor(context: Context) {
    private val prefManager = PreferenceManager(context)
    private var remoteDataSource: ReviewRemoteDataSource = ReviewRemoteDataSource(prefManager)
    suspend fun getReviews(): Resource<List<Review>> = remoteDataSource.getReviews()

}