package com.example.ecommerceapp.domain.repository

import com.example.ecommerceapp.data.model.RegistrationRequest
import com.example.ecommerceapp.data.model.RegistrationResponse


interface RegistrationRepository {
    suspend fun userRegistration(registrationRequest: RegistrationRequest): RegistrationResponse
}