package com.h5190007.bauhaus.data.datasource.remote

import com.h5190007.bauhaus.data.datasource.CategoryDataSource
import com.h5190007.bauhaus.data.model.CategoryResponse
import com.h5190007.bauhaus.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCategoryDataSource : CategoryDataSource {
    override fun getAllCategories(): Flow<Resource<CategoryResponse>> = flow {
        try {
            emit(Resource.Loading())

            val response = ItemsService.build().getAllCategories()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}
