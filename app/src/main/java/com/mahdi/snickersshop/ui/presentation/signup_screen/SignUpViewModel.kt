package com.mahdi.snickersshop.ui.presentation.signup_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahdi.snickersshop.data.repository.user.UserRepository
import com.mahdi.snickersshop.util.coroutineExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
) :ViewModel(){
    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val confirmPassword = MutableLiveData("")

    fun signUp(loggingResult:(String) -> Unit){
        viewModelScope.launch(coroutineExceptionHandler){
            val result = userRepository.signUp(name.value!! , email.value!! , password.value!!)
            loggingResult(result)
        }
    }
}