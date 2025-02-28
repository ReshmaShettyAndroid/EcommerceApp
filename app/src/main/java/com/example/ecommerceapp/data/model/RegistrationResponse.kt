package com.example.ecommerceapp.data.model

data class RegistrationResponse(
    val id: Int,
    val email: String,
    val password: String,
    val name: String,
    val avatar: String,
    val role: String
)