package com.example.ecommerceapp.network

import com.example.ecommerceapp.Utils.ApiUrlConstants.REFRESH_ACCESSTOKEN
import com.example.ecommerceapp.Utils.ApiUrlConstants.REGISTRATION
import com.example.ecommerceapp.data.model.RefreshTokenRequest
import com.example.ecommerceapp.data.model.RefreshTokenResponse
import com.example.ecommerceapp.data.model.RegistrationRequest
import com.example.ecommerceapp.data.model.RegistrationResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST(REFRESH_ACCESSTOKEN)
    suspend fun refreshToken(@Body request: RefreshTokenRequest): RefreshTokenResponse

    @POST(REGISTRATION)
    suspend fun userRegistration(@Body registrationRequest: RegistrationRequest): RegistrationResponse
 }

