package com.karuna.pages.data.repository

import android.content.Context
import com.karuna.pages.data.entities.Answer
import com.karuna.pages.data.entities.Category
import com.karuna.pages.data.entities.Question
import com.karuna.pages.data.remote.QuestionRemoteDataSource
import com.karuna.pages.utils.PreferenceManager
import com.karuna.pages.utils.Resource

class QuestionsRepository constructor(context: Context) {
    private val prefManager = PreferenceManager(context)
    private var remoteDataSource: QuestionRemoteDataSource = QuestionRemoteDataSource(prefManager)
    suspend fun getQuestions(): Resource<List<Question>> = remoteDataSource.getQuestions()
    suspend fun getAnswers(id: Long): Resource<List<Answer>> = remoteDataSource.getAnswers(id)
    suspend fun createQuestion(categoryId: Int, questionText: String): Resource<Question> {
        val question = Question(0, questionText, true, Category(categoryId))
        return remoteDataSource.createQuestion(question)
    }

    suspend fun createAnswer(questionId: Int, answerText: String): Resource<Answer> {
        val question = Question(questionId, "", true, Category(1))
        val answer = Answer("",answerText,prefManager.user!!,question)
        return remoteDataSource.createAnswer(answer, questionId.toLong())
    }

}