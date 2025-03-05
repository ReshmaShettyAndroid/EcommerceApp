package com.example.ecommerceapp.presentation.viewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.R
import com.example.ecommerceapp.Utils.ApiStatus
import com.example.ecommerceapp.Utils.CommonUtils
import com.example.ecommerceapp.data.model.RegistrationRequest
import com.example.ecommerceapp.data.model.RegistrationResponse
import com.example.ecommerceapp.domain.usecase.RegistrationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(var registrationUseCase: RegistrationUseCase) : ViewModel() {
    private val _userDetail = MutableLiveData<ApiStatus<RegistrationResponse>>()
    val userDetail = _userDetail
    @Inject lateinit var commonUtils: CommonUtils

    fun userRegistration(registrationRequest:RegistrationRequest) = viewModelScope.launch  {

            _userDetail.value = ApiStatus.Loading
            try {
                var userDetailReponse: RegistrationResponse =
                    registrationUseCase.userRegistration(registrationRequest)

                _userDetail.value = ApiStatus.Success(userDetailReponse)
            } catch (e: Exception) {
                _userDetail.value = ApiStatus.Failure(e.toString())
            }
    }

}