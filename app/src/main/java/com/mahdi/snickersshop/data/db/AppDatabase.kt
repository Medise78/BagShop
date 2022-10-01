package com.mahdi.snickersshop.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mahdi.snickersshop.data.model.Product

@Database(
    entities = [Product::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}