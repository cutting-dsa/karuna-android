package com.karuna.pages.data.network

import com.karuna.pages.data.entities.Entries
import com.karuna.pages.data.entities.Listing
import com.karuna.pages.data.entities.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("listings")
    suspend fun getListings() : Response<List<Listing>>

    @GET("entries")
    suspend fun getEntries(): Response<Entries>

    @POST("login")
    fun login(@Body email: String, @Body password: String): Response<User>

    @POST("register")
    fun register(@Body name: String, @Body email: String, @Body password: String): Response<User>
}