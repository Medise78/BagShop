package com.mahdi.snickersshop.data.model

data class SubmitOrder(
    val success: Boolean,
    val orderId: Int,
    val paymentLink: String
)
