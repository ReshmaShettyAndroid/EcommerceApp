package com.example.ecommerceapp.Utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.ecommerceapp.Utils.ApiKeyConstants.ACCESS_TOKEN_KEY
import com.example.ecommerceapp.Utils.ApiKeyConstants.REFRESH_TOKEN_KEY
import javax.inject.Inject

class CommonUtils @Inject constructor(context:Context) {
    val sharedPreferences = context.getSharedPreferences("UserPreferences", MODE_PRIVATE)

    // Save access token
    fun saveAccessToken(accessToken: String) {
        sharedPreferences.edit().putString(ACCESS_TOKEN_KEY, accessToken).apply()
    }

    // Save refresh token
    fun saveRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString(REFRESH_TOKEN_KEY, refreshToken).apply()
    }

    // Get access token
    fun getAccessToken(): String? {
        return sharedPreferences.getString(ACCESS_TOKEN_KEY, null)
    }

    // Get refresh token
    fun getRefreshToken(): String? {
        return sharedPreferences.getString(REFRESH_TOKEN_KEY, null)
    }

}