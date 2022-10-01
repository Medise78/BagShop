package com.mahdi.snickersshop.data.model

data class LoginResponse(
    val expireAt: Int,
    val message: String,
    val success: Boolean,
    val token: String
)
