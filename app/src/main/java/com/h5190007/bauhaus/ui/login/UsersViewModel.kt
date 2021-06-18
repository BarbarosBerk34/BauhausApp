package com.h5190007.bauhaus.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190007.bauhaus.data.model.UserResponse
import com.h5190007.bauhaus.data.repository.UserRepository
import com.h5190007.bauhaus.util.ResourceStatus
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepository()

    init {
        getAllUsers()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var usersLiveData = MutableLiveData<UserResponse>()
    var error =    MutableLiveData<Throwable>()


    fun getAllUsers()  = viewModelScope.launch {

        userRepository.getAllUsers()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("BBG",it.data.toString())
                        usersLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}