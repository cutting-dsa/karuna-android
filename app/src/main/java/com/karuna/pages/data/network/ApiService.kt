package com.karuna.pages.data.network

import com.karuna.pages.data.entities.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("listing/")
    suspend fun getListings(@Header("Authorization") auth: String): Response<List<Listing>>

    @GET("listing/byuser/{id}")
    suspend fun getMyListings(
        @Header("Authorization") auth: String, @Path("id") id: Long
    ): Response<List<Listing>>

    @GET("question/")
    suspend fun getQuestions(@Header("Authorization") auth: String): Response<List<Question>>

    @GET("answer/question-answers/{id}")
    suspend fun getAnswers(
        @Header("Authorization") auth: String,
        @Path("id") id: Long
    ): Response<List<Answer>>

    @GET("review/")
    suspend fun getReviews(@Header("Authorization") auth: String): Response<List<Review>>

    @GET("login")
    suspend fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Response<User>

    @POST("register")
    suspend fun register(
        @Body name: String,
        @Body email: String,
        @Body password: String
    ): Response<User>
}