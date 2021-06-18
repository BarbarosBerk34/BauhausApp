package com.h5190007.bauhaus.data.model
class CategoryResponse : ArrayList<CategoryResponseItem>()

data class CategoryResponseItem(
    val BannerUrl: String?,
    val Name: String?,
    val Products: List<Product>?
)

data class Product(
    val BrandName: String?,
    val Category: String?,
    val Detail: String?,
    val ImageUrl: String?,
    val Name: String?,
    val Price: String?
)