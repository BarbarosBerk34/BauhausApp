package com.h5190007.bauhaus.data.repository

import com.h5190007.bauhaus.data.datasource.remote.RemoteUserDataSource
import com.h5190007.bauhaus.data.model.UserResponse
import com.h5190007.bauhaus.util.Resource
import kotlinx.coroutines.flow.Flow

class UserRepository {
    private var userDataSource: RemoteUserDataSource? = null

    init {
        userDataSource = RemoteUserDataSource()
    }

    fun getAllUsers(): Flow<Resource<UserResponse>> {
        return userDataSource!!.getAllUsers()
    }
}