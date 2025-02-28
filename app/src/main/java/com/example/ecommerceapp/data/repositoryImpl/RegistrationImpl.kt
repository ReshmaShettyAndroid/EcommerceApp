package com.example.ecommerceapp.data.repositoryImpl

import com.example.ecommerceapp.data.model.RegistrationRequest
import com.example.ecommerceapp.data.model.RegistrationResponse
import com.example.ecommerceapp.domain.repository.RegistrationRepository
import com.example.ecommerceapp.network.ApiServiceImpl
import javax.inject.Inject

class RegistrationImpl  @Inject constructor(private val apiServiceImpl: ApiServiceImpl) :
    RegistrationRepository {
    override suspend fun userRegistration(registrationRequest: RegistrationRequest): RegistrationResponse {
        return apiServiceImpl.userRegistration(registrationRequest)
    }
}