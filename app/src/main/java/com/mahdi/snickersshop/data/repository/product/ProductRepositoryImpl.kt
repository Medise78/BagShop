package com.mahdi.snickersshop.data.repository.product

import com.mahdi.snickersshop.data.api.ApiService
import com.mahdi.snickersshop.data.db.ProductDao
import com.mahdi.snickersshop.data.model.Ads
import com.mahdi.snickersshop.data.model.Product
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val productDao: ProductDao
) : ProductRepository {
    override suspend fun getAllProducts(isInternetConnected: Boolean): List<Product> {
        if (isInternetConnected) {
            val data = apiService.getAllProducts()
            if (data.success) {
                productDao.insertOrUpdateData(data.products)
                return data.products
            }
        } else return productDao.getAllProducts()
        return emptyList()
    }

    override suspend fun getAllAds(isInternetConnected: Boolean): List<Ads> {
        if (isInternetConnected) {
            val data = apiService.getAllAds()
            if (data.success) {
                return data.ads
            }
        }
        return emptyList()
    }

    override suspend fun getProductById(productId: String): Product {
        return productDao.getProductWithId(productId)
    }

    override suspend fun getProductsWithCategoryName(categoryName: String): List<Product> {
        return productDao.getAllProductWithCategoryName(categoryName)
    }
}