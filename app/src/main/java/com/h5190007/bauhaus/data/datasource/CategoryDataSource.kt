package com.h5190007.bauhaus.data.datasource

import com.h5190007.bauhaus.data.model.CategoryResponse
import com.h5190007.bauhaus.util.Resource
import kotlinx.coroutines.flow.Flow

interface CategoryDataSource {
    fun getAllCategories(): Flow<Resource<CategoryResponse>>

}