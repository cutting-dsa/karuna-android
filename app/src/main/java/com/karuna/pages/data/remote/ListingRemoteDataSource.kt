package com.karuna.pages.data.remote

import com.karuna.pages.data.network.RestClient

class ListingRemoteDataSource: BaseDataSource() {
    suspend fun getListings() = getResult { RestClient.getInstance().getApiService().getListings() }
}