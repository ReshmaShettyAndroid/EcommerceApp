package com.example.ecommerceapp.presentation.view.ui

import android.util.Log
import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.Utils.ApiStatus
import com.example.ecommerceapp.Utils.CommonUtils
import com.example.ecommerceapp.data.model.RegistrationRequest
import com.example.ecommerceapp.data.model.RegistrationResponse
import com.example.ecommerceapp.presentation.view.activity.Greeting
import com.example.ecommerceapp.presentation.viewModel.RegistrationViewModel
import com.example.ecommerceapp.ui.theme.EcommerceAppTheme
import com.example.ecommerceapp.ui.theme.Purple40
import com.example.ecommerceapp.ui.theme.gradient
import javax.inject.Inject

@Composable
fun Registration(navController: NavHostController) {
    var registrationViewModel: RegistrationViewModel = hiltViewModel()

    Box(modifier = Modifier
        .background(gradient)
        .fillMaxSize(),
        content = {
            var registrationRequest by remember { mutableStateOf(RegistrationRequest("", "", "", "https://picsum.photos/800")) }
            var isButtonClicked by remember { mutableStateOf(false) }

            var userNameErrorState = remember { mutableStateOf("") }
            var emailErrorState = remember { mutableStateOf("") }
            var passwordErrorState = remember { mutableStateOf("") }


            LaunchedEffect(isButtonClicked, registrationRequest) {
                if (isButtonClicked) {
                    var isEmailValid = validateEmail(registrationRequest, emailErrorState)
                    var isUserValid = validateUserName(registrationRequest, userNameErrorState)
                    var isPasswordValid = validatePassword(registrationRequest, passwordErrorState)

                    if (isUserValid && isEmailValid && isPasswordValid)
                        registrationViewModel.userRegistration(registrationRequest)

                }
                isButtonClicked = false
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
            )
            {
                Text(
                    text = stringResource(id = R.string.registration),
                    color = Color.Black,
                    modifier = Modifier
                        .padding(50.dp)
                        .wrapContentHeight()
                        .align(Alignment.CenterHorizontally),
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.CenterHorizontally)
                ) {

                    OutlinedTextField(
                        value = registrationRequest.name,
                        onValueChange = {
                            registrationRequest = registrationRequest.copy(name = it)
                            validateUserName(registrationRequest, userNameErrorState)
                        },
                        label = { Text(text = stringResource(id = R.string.enter_username)) },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .align(Alignment.CenterHorizontally),
                        enabled = true
                    )
                    showErrorMsg(userNameErrorState.value)

                    OutlinedTextField(
                        value = registrationRequest.password,
                        onValueChange = {
                            registrationRequest =
                                registrationRequest.copy(password = it)
                            validatePassword(registrationRequest, passwordErrorState)
                        },
                        label = { Text(text = stringResource(id = R.string.enter_password)) },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .align(Alignment.CenterHorizontally),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        isError = passwordErrorState.value.isNotEmpty(),
                    )
                    showErrorMsg(passwordErrorState.value)

                    OutlinedTextField(
                        value = registrationRequest.email,
                        onValueChange = {
                            registrationRequest = registrationRequest.copy(email = it)
                            validateEmail(registrationRequest, emailErrorState)
                        },
                        label = { Text(text = stringResource(id = R.string.enter_email)) },
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .align(Alignment.CenterHorizontally),
                        isError = emailErrorState.value.isNotEmpty(),
                    )
                    showErrorMsg(emailErrorState.value)

                    Button(
                        onClick = {
                            isButtonClicked = true
                        },
                        modifier = Modifier
                            .padding(50.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(200.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Purple40)
                    ) {
                        Text(
                            modifier = Modifier.padding(50.dp, 0.dp, 50.dp, 0.dp),
                            text = stringResource(id = R.string.submit), color = Color.White
                        )
                    }
                }
            }

            //Api call management
            handleLoginApiCall(
                Modifier.align(Alignment.Center),
                registrationViewModel,
                navController
            )

        })
}

fun validateUserName(
    registrationRequest: RegistrationRequest,
    userNameErrorState: MutableState<String>
): Boolean {
    return if (registrationRequest.name.isEmpty()) {
        userNameErrorState.value = "Enter UserName"
        false
    } else {
        userNameErrorState.value = ""
        true
    }
}

fun validatePassword(
    registrationRequest: RegistrationRequest,
    passwordErrorState:  MutableState<String>
): Boolean {
    return if (registrationRequest.password.length < 4) {
        passwordErrorState.value = "Password must be at least 4 characters"
        false
    } else {
        passwordErrorState.value = ""
        true
    }
}
fun validateEmail(
    registrationRequest: RegistrationRequest,
    emailErrorState :MutableState<String>
): Boolean {
    return if (registrationRequest.email.isEmpty()) {
        emailErrorState.value = "Enter Email"
        false
    } else if (!Patterns.EMAIL_ADDRESS.matcher(registrationRequest.email).matches()) {
        emailErrorState.value = "Enter valid email Id"
        false
    } else {
        emailErrorState.value = ""
        true
    }
}

@Composable
fun showErrorMsg(errorState: String) {
    if (errorState.isNotEmpty()) {
        Text(
            text = errorState,
            color = Color.Red,
            modifier = Modifier.padding(start = 25.dp)
        )
    }
}

@Composable
fun handleLoginApiCall(
    modifier: Modifier,
    registrationViewModel: RegistrationViewModel,
    navController: NavHostController
) {
    val apiStatus by registrationViewModel.userDetail.observeAsState()
    //Api call management
    when (apiStatus) {
        is ApiStatus.Loading -> {
            CircularProgressIndicator(modifier = modifier)
        }

        is ApiStatus.Success -> {
            var a = (apiStatus as ApiStatus.Success<RegistrationResponse>).data
            Log.d("TAG", "TAG: Response:-" + a.toString())
        }

        is ApiStatus.Failure -> {
            ShowAlertDialog((apiStatus as ApiStatus.Failure).msg, modifier)
        }

        else -> {}
    }
}

@Composable
fun ShowAlertDialog(txtmsg: String, modifier: Modifier) {
    var showDialog by remember { mutableStateOf(true) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Set the state to false to dismiss the dialog
                showDialog = false
            },
            title = { Text(text = "Error") },
            text = { Text(txtmsg) },
            confirmButton = {
                Button(modifier = modifier,
                    onClick = {
                        // Dismiss the dialog on positive button click
                        showDialog = false
                    }
                ) {
                    Text("OK")
                }
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun RegistrationPreview() {
    val navController = rememberNavController()
    EcommerceAppTheme {
        Registration(navController)
    }
}
