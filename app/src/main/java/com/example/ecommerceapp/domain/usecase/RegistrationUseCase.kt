package com.example.ecommerceapp.domain.usecase

import com.example.ecommerceapp.data.model.RegistrationRequest
import com.example.ecommerceapp.data.model.RegistrationResponse
import com.example.ecommerceapp.domain.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationUseCase @Inject constructor(private val registrationRepository: RegistrationRepository) {
    suspend fun userRegistration(registrationRequest: RegistrationRequest): RegistrationResponse {
        return registrationRepository.userRegistration(registrationRequest)
    }

}
