package com.example.ecommerceapp.network

import com.example.ecommerceapp.data.model.RefreshTokenRequest
import com.example.ecommerceapp.data.model.RefreshTokenResponse
import com.example.ecommerceapp.data.model.RegistrationRequest
import com.example.ecommerceapp.data.model.RegistrationResponse
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: ApiService) {
    suspend fun refreshToken(refreshTokenRequest: RefreshTokenRequest): RefreshTokenResponse = apiService.refreshToken(refreshTokenRequest)
    suspend fun userRegistration(registrationRequest: RegistrationRequest): RegistrationResponse = apiService.userRegistration(registrationRequest)

}