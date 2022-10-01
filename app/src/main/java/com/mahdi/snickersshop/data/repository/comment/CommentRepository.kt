package com.mahdi.snickersshop.data.repository.comment

import com.mahdi.snickersshop.data.model.Comment

interface CommentRepository {
    suspend fun getAllComments(productId: String): List<Comment>
    suspend fun addNewComment(productId: String, text: String, isSuccess: (String) -> Unit)
}