package com.mahdi.snickersshop.ui.presentation.buy_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdi.snickersshop.data.model.Product
import com.mahdi.snickersshop.data.repository.cart.CartRepository
import com.mahdi.snickersshop.data.repository.user.UserRepository
import com.mahdi.snickersshop.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyScreenViewModel @Inject constructor(
    private val cartRepository: CartRepository,
    private val userRepository: UserRepository
) : ViewModel() {
    val totalPrice = mutableStateOf(0)
    val productList = mutableStateOf<List<Product>>(emptyList())
    val isChangingNumber = mutableStateOf(Pair("", false))

    fun loadData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val data = cartRepository.getUserCartInfo()
            totalPrice.value = data.totalPrice
            productList.value = data.productList
        }
    }

    fun addItem(productId: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            isChangingNumber.value = isChangingNumber.value.copy(
                productId, true
            )
            val result = cartRepository.addProduct(productId)
            if (result) {
                loadData()
                cartRepository.getUserCartSize()
            }
            delay(100)
            isChangingNumber.value = isChangingNumber.value.copy(
                productId, false
            )
        }
    }

    fun removeItem(productId: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            isChangingNumber.value = isChangingNumber.value.copy(
                productId, true
            )
            val result = cartRepository.removeProductFromCart(productId)
            if (result) {
                loadData()
            }
            delay(100)
            isChangingNumber.value = isChangingNumber.value.copy(
                productId, false
            )
        }
    }

    fun getUserLocation(): Pair<String, String> {
        return userRepository.getUserLocation()
    }

    fun setUserLocation(address: String, postalCode: String) {
        userRepository.saveUserLocation(address, postalCode)
    }

    fun purchaseAll(address: String, postalCode: String, isSuccess: (Boolean, String) -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val result = cartRepository.submitOrder(address, postalCode)
            isSuccess(result.success, result.paymentLink)
        }
    }

    fun setPurchaseStatus(status: Int) {
        cartRepository.setPurchaseState(status)
    }
}