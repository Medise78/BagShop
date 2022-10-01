package com.mahdi.snickersshop.ui.presentation.setting_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.mahdi.snickersshop.data.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    val email = mutableStateOf("")
    val address = mutableStateOf("")
    val postalCode = mutableStateOf("")
    val loginTime = mutableStateOf("")
    val locationDialog = mutableStateOf(false)

    fun loadUserData(){
        email.value = userRepository.getUsername().toString()
        loginTime.value = userRepository.getUserTimeLogin()
        postalCode.value = userRepository.getUserLocation().second
        address.value = userRepository.getUserLocation().first
    }

    fun logOut() {
        userRepository.signOut()
    }

    fun setUserLocation(address: String, postalCode: String) {
        userRepository.saveUserLocation(address, postalCode)
    }
}