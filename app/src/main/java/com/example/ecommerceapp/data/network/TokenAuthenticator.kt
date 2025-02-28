package com.example.ecommerceapp.network

import com.example.ecommerceapp.Utils.ApiHeaderConstants.HEADER_AUTHORIZATION
import com.example.ecommerceapp.Utils.ApiHeaderConstants.HEADER_AUTHORIZATION_TYPE
import com.example.ecommerceapp.Utils.ApiHeaderConstants.HEADER_CONTENT_TYPE
import com.example.ecommerceapp.Utils.ApiHeaderConstants.HEADER_CONTENT_TYPE_VALUE
import com.example.ecommerceapp.Utils.CommonUtils
import com.example.ecommerceapp.data.model.RefreshTokenRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

class TokenAuthenticator : Authenticator {

    @Inject lateinit var commonUtils: CommonUtils
    @Inject lateinit var apiService: ApiServiceImpl
    // AtomicBoolean in order to avoid race condition
    private var tokenRefreshInProgress: AtomicBoolean = AtomicBoolean(false)
    private var request: Request? = null

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            request = null

            // Checking if a token refresh call is already in progress or not The first request will enter the if block Later requests will enter the else block
            if (!tokenRefreshInProgress.get()) {
                tokenRefreshInProgress.set(true)

                refreshToken()
                request = buildRequest(response.request.newBuilder())
                tokenRefreshInProgress.set(false)
            } else {
                // Waiting for the ongoing request to finish So that we don't refresh our token multiple times
                waitForRefresh(response)
            }

            // return null to stop retrying once responseCount returns 3 or above.
            if (responseCount(response) >= 3) {
                null
            } else request
        }
    }

    // Refresh your token here and save them.
    private suspend fun refreshToken() {
        // Simulating a token refresh request
        delay(200)

       //call api for token
        var refreshTokenResponse = apiService.refreshToken(RefreshTokenRequest(commonUtils.getRefreshToken()!!))

        //save in preference
        commonUtils.saveAccessToken(refreshTokenResponse.access_token)
        commonUtils.saveRefreshToken(refreshTokenResponse.refresh_token)

        delay(200)

    }

    // Queuing the requests with delay
    private suspend fun waitForRefresh(response: Response) {
        while (tokenRefreshInProgress.get()) {
            delay(100)
        }
        request = buildRequest(response.request.newBuilder())
    }

    private fun responseCount(response: Response?): Int {
        var result = 1
        while (response?.priorResponse != null && result <= 3) {
            result++
        }
        return result
    }

    // Build a new request with new access token
    private fun buildRequest(requestBuilder: Request.Builder): Request {
        return requestBuilder
            .header(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_VALUE)
            .header(HEADER_AUTHORIZATION, HEADER_AUTHORIZATION_TYPE + commonUtils.getAccessToken()!!)
            .build()
    }
}
