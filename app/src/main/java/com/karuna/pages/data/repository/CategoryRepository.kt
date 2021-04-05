package com.karuna.pages.data.repository

import android.content.Context
import com.karuna.pages.data.entities.Category
import com.karuna.pages.data.remote.CategoryRemoteDataSource
import com.karuna.pages.utils.PreferenceManager
import com.karuna.pages.utils.Resource

class CategoryRepository constructor(context: Context) {
    private val prefManager = PreferenceManager(context)
    private var remoteDataSource: CategoryRemoteDataSource = CategoryRemoteDataSource(prefManager)
    suspend fun getCategories(): Resource<List<Category>> = remoteDataSource.getCategories()
}