package com.mahdi.snickersshop.data.repository.product

import com.mahdi.snickersshop.data.model.Ads
import com.mahdi.snickersshop.data.model.Product

interface ProductRepository {
    suspend fun getAllProducts(isInternetConnected: Boolean): List<Product>
    suspend fun getAllAds(isInternetConnected: Boolean): List<Ads>
    suspend fun getProductById(productId: String): Product
    suspend fun getProductsWithCategoryName(categoryName: String): List<Product>
}