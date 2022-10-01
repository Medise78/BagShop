package com.mahdi.snickersshop.data.repository.cart

import com.mahdi.snickersshop.data.model.Checkout
import com.mahdi.snickersshop.data.model.SubmitOrder
import com.mahdi.snickersshop.data.model.UserCartInfo

interface CartRepository {
    suspend fun addProduct(productId: String): Boolean
    suspend fun removeProductFromCart(productId: String): Boolean
    suspend fun getUserCartInfo(): UserCartInfo
    suspend fun getUserCartSize(): Int
    suspend fun submitOrder(address: String, postalCode: String): SubmitOrder
    suspend fun checkOut(orderId: Int): Checkout
    fun setOrderId(orderId: String)
    fun getOrderId(): String
    fun setPurchaseState(status: Int)
    fun getPurchaseState(): Int
}