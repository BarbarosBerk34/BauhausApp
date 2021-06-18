package com.h5190007.bauhaus.data.datasource.remote

import com.h5190007.bauhaus.data.model.CategoryResponse
import com.h5190007.bauhaus.data.model.UserResponse
import com.h5190007.bauhaus.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ItemsService {
    @GET("users.json")
    suspend fun getAllUsers(): Response<UserResponse>

    @GET("categories_products.json")
    suspend fun getAllCategories(): Response<CategoryResponse>

    companion object {

        fun build(): ItemsService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .build()

            return retrofit.create(ItemsService::class.java)
        }
    }
}