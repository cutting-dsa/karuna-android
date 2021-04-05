package com.karuna.pages.data.network

import com.karuna.pages.data.entities.*
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("listing/")
    suspend fun getListings(@Header("Authorization") auth: String): Response<List<Listing>>

    @GET("listing/{id}")
    suspend fun getListing(
        @Header("Authorization") auth: String,
        @Path("id") id: Long
    ): Response<Listing>

    @GET("listing/byuser/{id}")
    suspend fun getMyListings(
        @Header("Authorization") auth: String, @Path("id") id: Long
    ): Response<List<Listing>>

    @GET("question/")
    suspend fun getQuestions(@Header("Authorization") auth: String): Response<List<Question>>

    @GET("answer/question/{id}")
    suspend fun getAnswers(
        @Header("Authorization") auth: String,
        @Path("id") id: Long
    ): Response<List<Answer>>

    @GET("review/")
    suspend fun getReviews(@Header("Authorization") auth: String): Response<List<Review>>

    @POST("review/create")
    suspend fun reviewListing(
        @Header("Authorization") auth: String,
        @Body review: Review
    ): Response<Review>


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

    @GET("category/")
    suspend fun getCategories(@Header("Authorization") auth: String): Response<List<Category>>

    @POST("question/")
    suspend fun createQuestion(
        @Header("Authorization") auth: String,
        @Body question: Question
    ): Response<Question>
}