package com.mahdi.snickersshop.data.repository.user

interface UserRepository {
    suspend fun signUp(name: String, username: String, password: String): String
    suspend fun signIn(name: String, password: String): String

    fun loadToken()
    fun signOut()

    fun saveToken(newToken: String)
    fun getToken(): String?
    fun saveUsername(name: String)
    fun getUsername(): String?
    fun saveUserLocation(address: String, postalCode: String)
    fun getUserLocation(): Pair<String, String>
    fun setUserTimeLogin()
    fun getUserTimeLogin(): String
}