package com.karuna.pages.data.repository

import android.content.Context
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.remote.ListingRemoteDataSource
import com.karuna.pages.utils.PreferenceManager
import com.karuna.pages.utils.Resource

class ListingRepository constructor(context: Context) {
    private val prefManager = PreferenceManager(context)
    private var remoteDataSource: ListingRemoteDataSource = ListingRemoteDataSource(prefManager)
    suspend fun getListings(): Resource<List<Listing>> = remoteDataSource.getListings()
    suspend fun getMyListings(): Resource<List<Listing>> = remoteDataSource.getMyListings()
}