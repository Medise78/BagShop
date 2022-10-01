package com.mahdi.snickersshop.ui.presentation.add_to_cart_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdi.snickersshop.data.model.Comment
import com.mahdi.snickersshop.data.model.Product
import com.mahdi.snickersshop.data.repository.cart.CartRepository
import com.mahdi.snickersshop.data.repository.comment.CommentRepository
import com.mahdi.snickersshop.data.repository.product.ProductRepository
import com.mahdi.snickersshop.util.EMPTY_PRODUCT
import com.mahdi.snickersshop.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToCartViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val commentRepository: CommentRepository,
    private val productRepository: ProductRepository
) : ViewModel() {
    val thisProduct = mutableStateOf<Product>(EMPTY_PRODUCT)
    val badgeNumber = mutableStateOf(0)
    val comments = mutableStateOf<List<Comment>>(emptyList())
    val isAddingProduct = mutableStateOf(false)

    fun loadData(productId: String, isInternetConnected: Boolean) {
        viewModelScope.launch(coroutineExceptionHandler) {
            thisProduct.value = productRepository.getProductById(productId)
            if (isInternetConnected) {
                loadBadgeNumber()
                loadComments(productId)
            }
        }
    }

    fun addNewComment(productId: String, text: String, isSuccess: (String) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            commentRepository.addNewComment(productId, text, isSuccess)
            delay(100)
            comments.value = commentRepository.getAllComments(productId)
        }
    }

    fun addProductToCart(productId: String, isSuccess: (String) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            isAddingProduct.value = true
            val data = cartRepository.addProduct(productId)
            delay(100)
            isAddingProduct.value = false
            if (data) isSuccess("Product Added To Cart")
            else isSuccess("Product Not Added")
        }
    }

    fun loadComments(productId: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            comments.value = commentRepository.getAllComments(productId)
        }
    }

    fun loadBadgeNumber() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val data = cartRepository.getUserCartSize()
            badgeNumber.value = data
        }
    }
}