package com.mahdi.snickersshop.data.repository.comment

import com.google.gson.JsonObject
import com.mahdi.snickersshop.data.api.ApiService
import com.mahdi.snickersshop.data.model.Comment
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : CommentRepository {
    override suspend fun getAllComments(productId: String): List<Comment> {
        val jsonObject = JsonObject().apply {
            addProperty("productId", productId)
        }
        val data = apiService.getAllComments(jsonObject)
        if (data.success) return data.comments
        return emptyList()
    }

    override suspend fun addNewComment(
        productId: String,
        text: String,
        isSuccess: (String) -> Unit
    ) {
        val jsonObject = JsonObject().apply {
            addProperty("productId", productId)
            addProperty("text", text)
        }
        val data = apiService.addNewComment(jsonObject)
        if (data.success) isSuccess(data.message)
    }
}