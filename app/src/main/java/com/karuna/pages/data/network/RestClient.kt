package com.karuna.pages.data.network

import com.karuna.pages.utils.PreferenceManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestClient private constructor(
    preferenceManager: PreferenceManager,
    intercept: Boolean = true
) {
    companion object {
        private lateinit var mApiServices: ApiService
        private var mInstance: RestClient? = null
        fun getInstance(
            preferenceManager: PreferenceManager,
            intercept: Boolean = true
        ): RestClient {
            if (mInstance == null) {
                synchronized(this) {
                    mInstance = RestClient(preferenceManager, intercept)
                }
            }
            return mInstance!!
        }
    }

    init {
        val username = preferenceManager.user?.username.orEmpty()
        val password = preferenceManager.password

        val builder = OkHttpClient().newBuilder()
       // if (intercept) {
//            builder.addInterceptor(BasicAuthInterceptor(username, password))
        //}
       // builder.addInterceptor(BasicAuthInterceptor("karuna", "password"))
        val okHttpClient = builder
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder().baseUrl(URL.API_BASE_URL.value)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApiServices = retrofit.create(ApiService::class.java)
    }

    fun getApiService() = mApiServices
}