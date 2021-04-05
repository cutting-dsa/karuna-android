package com.karuna.pages.data.repository

import android.content.Context
import com.karuna.pages.data.entities.Category
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.entities.Review
import com.karuna.pages.data.remote.ListingRemoteDataSource
import com.karuna.pages.utils.PreferenceManager
import com.karuna.pages.utils.Resource

class ListingRepository constructor(context: Context) {
    private val prefManager = PreferenceManager(context)
    private var remoteDataSource: ListingRemoteDataSource = ListingRemoteDataSource(prefManager)
    suspend fun getListings(): Resource<List<Listing>> = remoteDataSource.getListings()
    suspend fun getMyListings(): Resource<List<Listing>> = remoteDataSource.getMyListings()
    suspend fun reviewListing(comment: String, rating: Int, listingId: Int): Resource<Review> {
        val review = prefManager.user?.let { Review(0,comment,
            Listing(id = listingId,
            "",1,",","",1,it, Category("1"),
            1,)
            ,rating, it,1) }
        return remoteDataSource.reviewListing(review!!)
    }

    suspend fun getListing(id: Int): Resource<Listing> = remoteDataSource.getListing(id.toLong())
}