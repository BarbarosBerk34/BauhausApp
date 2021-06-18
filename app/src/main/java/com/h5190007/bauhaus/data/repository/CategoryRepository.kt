package com.h5190007.bauhaus.data.repository

import com.h5190007.bauhaus.data.datasource.remote.RemoteCategoryDataSource
import com.h5190007.bauhaus.data.model.CategoryResponse
import com.h5190007.bauhaus.util.Resource
import kotlinx.coroutines.flow.Flow

class CategoryRepository {
    private var categoryDataSource: RemoteCategoryDataSource? = null

    init {
        categoryDataSource = RemoteCategoryDataSource()
    }

    fun getAllCategories(): Flow<Resource<CategoryResponse>> {
        return categoryDataSource!!.getAllCategories()
    }
}