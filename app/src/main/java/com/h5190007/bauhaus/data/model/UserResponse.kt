package com.h5190007.bauhaus.data.model

class UserResponse : ArrayList<UserResponseItem>()

data class UserResponseItem(
    val Adi: String?,
    val Email: String?,
    val Parola: String?,
    val Soyadi: String?
)