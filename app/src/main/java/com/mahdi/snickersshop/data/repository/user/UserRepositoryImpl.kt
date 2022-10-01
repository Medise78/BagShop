package com.mahdi.snickersshop.data.repository.user

import android.content.SharedPreferences
import com.google.gson.JsonObject
import com.mahdi.snickersshop.data.api.ApiService
import com.mahdi.snickersshop.util.UserInfo
import com.mahdi.snickersshop.util.VALUE_SUCCESS
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : UserRepository {
    override suspend fun signUp(name: String, username: String, password: String): String {
        val jsonObject = JsonObject().apply {
            addProperty("name", name)
            addProperty("email", username)
            addProperty("password", password)
        }
        val result = apiService.signUp(jsonObject)
        return if (result.success) {
            UserInfo.refreshToken(username, result.token)
            saveUsername(username)
            saveToken(result.token)
            setUserTimeLogin()
            VALUE_SUCCESS
        } else result.message
    }

    override suspend fun signIn(name: String, password: String): String {
        val jsonObject = JsonObject().apply {
            addProperty("email", name)
            addProperty("password", password)
        }
        val result = apiService.signIn(jsonObject)
        return if (result.success) {
            UserInfo.refreshToken(name, result.token)
            saveUsername(name)
            saveToken(result.token)
            setUserTimeLogin()
            VALUE_SUCCESS
        } else result.message
    }

    override fun loadToken() {
        UserInfo.refreshToken(getUsername(), getToken())
    }

    override fun signOut() {
        UserInfo.refreshToken(null, null)
        sharedPreferences.edit().clear().apply()
    }

    override fun saveToken(newToken: String) {
        sharedPreferences.edit().putString("token", newToken).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    override fun saveUsername(name: String) {
        sharedPreferences.edit().putString("userName", name).apply()
    }

    override fun getUsername(): String? {
        return sharedPreferences.getString("userName", null)
    }

    override fun saveUserLocation(address: String, postalCode: String) {
        sharedPreferences.edit().putString("address", address).apply()
        sharedPreferences.edit().putString("postalCode", postalCode).apply()
    }

    override fun getUserLocation(): Pair<String, String> {
        val address = sharedPreferences.getString("address", "Click to add")!!
        val postalCode = sharedPreferences.getString("postalCode", "Click to add")!!
        return Pair(address, postalCode)
    }

    override fun setUserTimeLogin() {
        val currentTime = System.currentTimeMillis()
        sharedPreferences.edit().putString("loginTime", currentTime.toString()).apply()
    }

    override fun getUserTimeLogin(): String {
        return sharedPreferences.getString("loginTime", "0")!!
    }
}