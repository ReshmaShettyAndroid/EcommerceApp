package com.example.ecommerceapp.data.model

data class RegistrationRequest(
    val name: String,
    val email: String,
    val password: String,
    val avatar: String
)