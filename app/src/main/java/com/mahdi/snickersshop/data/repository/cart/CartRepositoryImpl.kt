package com.mahdi.snickersshop.data.repository.cart

import android.content.SharedPreferences
import com.google.gson.JsonObject
import com.mahdi.snickersshop.data.api.ApiService
import com.mahdi.snickersshop.data.model.Checkout
import com.mahdi.snickersshop.data.model.SubmitOrder
import com.mahdi.snickersshop.data.model.UserCartInfo
import com.mahdi.snickersshop.util.NO_PAYMENT
import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : CartRepository {
    override suspend fun addProduct(productId: String): Boolean {
        val jsonObject = JsonObject().apply {
            addProperty("productId", productId)
        }
        val data = apiService.addProductToCart(jsonObject)
        return data.success
    }

    override suspend fun removeProductFromCart(productId: String): Boolean {
        val jsonObject = JsonObject().apply {
            addProperty("productId", productId)
        }
        val data = apiService.removeFromCart(jsonObject)
        return data.success
    }

    override suspend fun getUserCartInfo(): UserCartInfo {
        return apiService.getUserCart()
    }

    override suspend fun getUserCartSize(): Int {
        val data = apiService.getUserCart()
        if (data.success) {
            var counter = 0
            data.productList.forEach { counter += (it.quantity ?: "0").toInt() }
            return counter
        }
        return 0
    }

    override suspend fun submitOrder(address: String, postalCode: String): SubmitOrder {
        val jsonObject = JsonObject().apply {
            addProperty("address", address)
            addProperty("postalCode", postalCode)
        }
        val result = apiService.submitOrder(jsonObject)
        setOrderId(result.orderId.toString())
        return result
    }

    override suspend fun checkOut(orderId: Int): Checkout {
        val jsonObject = JsonObject().apply {
            addProperty("orderId", orderId)
        }
        return apiService.checkOut(jsonObject)
    }

    override fun setOrderId(orderId: String) {
        sharedPreferences.edit().putString("orderId", orderId).apply()
    }

    override fun getOrderId(): String {
        return sharedPreferences.getString("orderId", "0")!!
    }

    override fun setPurchaseState(status: Int) {
        sharedPreferences.edit().putInt("status", status).apply()
    }

    override fun getPurchaseState(): Int {
        return sharedPreferences.getInt("status", NO_PAYMENT)
    }
}