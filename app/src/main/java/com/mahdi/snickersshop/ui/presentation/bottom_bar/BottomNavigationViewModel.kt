package com.mahdi.snickersshop.ui.presentation.bottom_bar

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdi.snickersshop.data.repository.cart.CartRepository
import com.mahdi.snickersshop.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BottomNavigationViewModel @Inject constructor(
    private val cartRepository: CartRepository
) :ViewModel(){
    val badgeNumber = mutableStateOf(0)

    fun loadBadgeNumber(){
        viewModelScope.launch(coroutineExceptionHandler){
            badgeNumber.value = cartRepository.getUserCartSize()
        }
    }
}