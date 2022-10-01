package com.mahdi.snickersshop.ui.presentation.detail_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.mahdi.snickersshop.data.model.Product
import com.mahdi.snickersshop.data.repository.product.ProductRepository
import com.mahdi.snickersshop.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val liveData = savedStateHandle.getLiveData("isInternetConnected", true)

    private val _data = MutableLiveData<List<Product>>()
    val data: LiveData<List<Product>> = _data


    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val data = productRepository.getAllProducts(liveData.value ?: false)
            _data.value = data
        }
    }
}