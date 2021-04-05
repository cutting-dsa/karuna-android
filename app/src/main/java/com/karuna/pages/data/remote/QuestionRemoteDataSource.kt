package com.karuna.pages.data.remote

import com.karuna.pages.data.entities.Question
import com.karuna.pages.data.network.RestClient
import com.karuna.pages.utils.PreferenceManager
import okhttp3.Credentials

class QuestionRemoteDataSource constructor(preferenceManager: PreferenceManager) :
    BaseDataSource() {
    private val prefManager: PreferenceManager = preferenceManager
    private var credentials: String = Credentials.basic("karuna", "password")
    suspend fun getQuestions() =
        getResult { RestClient.getInstance(prefManager).getApiService().getQuestions(credentials) }

    suspend fun getAnswers(id: Long) = getResult {
        RestClient.getInstance(prefManager).getApiService().getAnswers(credentials, id)
    }

    suspend fun createQuestion(question: Question) = getResult {
        RestClient.getInstance(prefManager).getApiService().createQuestion(credentials, question)
    }
}