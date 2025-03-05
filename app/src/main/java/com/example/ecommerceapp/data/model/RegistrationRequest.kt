package com.example.ecommerceapp.data.model

data class RegistrationRequest(
    var name: String,
    var email: String,
    var password: String,
    val avatar: String
)