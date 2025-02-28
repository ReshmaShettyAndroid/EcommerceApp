package com.example.ecommerceapp.Utils

object ApiUrlConstants {
    const val BASE_URL = "https://api.escuelajs.co/api/v1/"
    const val REGISTRATION = "/users/"
    const val REFRESH_ACCESSTOKEN = "auth/refresh-token"
}

object ApiKeyConstants {

    const val ACCESS_TOKEN_KEY = "access_token"
    const val REFRESH_TOKEN_KEY = "refresh_token"

}

object ApiHeaderConstants {
    const val HEADER_AUTHORIZATION = "Authorization"
    const val HEADER_CONTENT_TYPE = "Content-Type"
    const val HEADER_CONTENT_TYPE_VALUE = "application/json"
    const val HEADER_AUTHORIZATION_TYPE = "Bearer "
}