package com.karuna.pages.data.repository

import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.remote.ListingRemoteDataSource
import com.karuna.pages.utils.Resource

class ListingRepository {

    private var remoteDataSource: ListingRemoteDataSource = ListingRemoteDataSource()
    suspend fun getListings(): Resource<List<Listing>> = remoteDataSource.getListings()

}