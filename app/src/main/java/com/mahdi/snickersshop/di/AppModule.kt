package com.mahdi.snickersshop.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.mahdi.snickersshop.data.api.ApiService
import com.mahdi.snickersshop.data.db.AppDatabase
import com.mahdi.snickersshop.data.db.ProductDao
import com.mahdi.snickersshop.data.repository.cart.CartRepository
import com.mahdi.snickersshop.data.repository.cart.CartRepositoryImpl
import com.mahdi.snickersshop.data.repository.comment.CommentRepository
import com.mahdi.snickersshop.data.repository.comment.CommentRepositoryImpl
import com.mahdi.snickersshop.data.repository.product.ProductRepository
import com.mahdi.snickersshop.data.repository.product.ProductRepositoryImpl
import com.mahdi.snickersshop.data.repository.user.UserRepository
import com.mahdi.snickersshop.data.repository.user.UserRepositoryImpl
import com.mahdi.snickersshop.util.BASE_URL
import com.mahdi.snickersshop.util.UserInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "PRODUCT_DB"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): ApiService {
        val okHttp = OkHttpClient.Builder()
            .addInterceptor {
                val oldRequest = it.request()
                val newRequest = oldRequest.newBuilder()
                if (UserInfo.token != null) {
                    newRequest.addHeader("Authorization", UserInfo.token!!)
                }
                newRequest.addHeader("Accept", "application/json")
                newRequest.method(oldRequest.method, oldRequest.body)
                return@addInterceptor it.proceed(newRequest.build())
            }.build()
        val retrofit = Retrofit.Builder()
            .client(okHttp)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        apiService: ApiService,
        sharedPreferences: SharedPreferences
    ): UserRepository {
        return UserRepositoryImpl(apiService, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        apiService: ApiService,
        appDatabase: AppDatabase
    ): ProductRepository {
        return ProductRepositoryImpl(apiService, appDatabase.productDao())
    }

    @Provides
    @Singleton
    fun provideCommentRepository(
        apiService: ApiService
    ): CommentRepository {
        return CommentRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideCartRepository(
        apiService: ApiService,
        sharedPreferences: SharedPreferences
    ): CartRepository {
        return CartRepositoryImpl(apiService, sharedPreferences)
    }

    @Provides
    @Singleton
    fun sharedPref(@ApplicationContext context: Context):SharedPreferences = context.getSharedPreferences("data" , Context.MODE_PRIVATE)

}