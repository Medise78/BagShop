package com.mahdi.snickersshop.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mahdi.snickersshop.data.model.Product

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateData(product: List<Product>)

    @Query("SELECT * FROM product_table")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM product_table WHERE productId = :productId")
    suspend fun getProductWithId(productId: String): Product

    @Query("SELECT * FROM product_table WHERE category = :categoryName")
    suspend fun getAllProductWithCategoryName(categoryName: String): List<Product>
}