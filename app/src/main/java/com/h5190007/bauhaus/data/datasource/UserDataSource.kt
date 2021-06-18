package com.h5190007.bauhaus.data.datasource

import com.h5190007.bauhaus.data.model.UserResponse
import com.h5190007.bauhaus.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun getAllUsers(): Flow<Resource<UserResponse>>

}