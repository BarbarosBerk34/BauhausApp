package com.h5190007.bauhaus.ui.categories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190007.bauhaus.data.model.CategoryResponse
import com.h5190007.bauhaus.data.repository.CategoryRepository
import com.h5190007.bauhaus.util.ResourceStatus
import kotlinx.coroutines.launch

class CategoriesViewModel: ViewModel() {
    private val categoryRepository: CategoryRepository = CategoryRepository()

    init {
        getAllCategories()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var categoriesLiveData = MutableLiveData<CategoryResponse>()
    var error =    MutableLiveData<Throwable>()


    fun getAllCategories()  = viewModelScope.launch {

        categoryRepository.getAllCategories()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        Log.e("BBG",it.data.toString())
                        categoriesLiveData.postValue(it.data!!)
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