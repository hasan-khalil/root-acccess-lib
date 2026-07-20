package com.example.firepassword.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.firepassword.data.remote.FirebaseSource
import com.example.firepassword.data.repository.AuthRepositoryImpl
import com.example.firepassword.ui.navigation.Routes
import com.example.firepassword.ui.register.RegisterViewModel
import com.example.firepassword.ui.widgets.AppEmailTextField
import com.example.firepassword.ui.widgets.AppTextField
import com.example.firepassword.ui.widgets.PasswordField
import com.example.firepassword.ui.widgets.PrimaryButton
import com.example.firepassword.utils.Resource

@Composable fun LoginScreen( navController: NavController ) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val repository = remember {
        AuthRepositoryImpl(
            FirebaseSource()
        )
    }



    val viewModel: LoginViewModel = viewModel(
        factory = LoginViewModel.Factory(repository)
    )
    val state by viewModel.state.collectAsState()


    val snackBarHostState = remember {
        SnackbarHostState()
    }



    LaunchedEffect(state) {

        when (state) {

            is Resource.Success -> {

                navController.navigate(
                    Routes.Home.route
                ) {

                    popUpTo(
                        Routes.Login.route
                    ) {
                        inclusive = true
                    }

                }

            }

            is Resource.Error -> {
                snackBarHostState.showSnackbar(
                    (state as Resource.Error).message
                )
            }

            else -> {}
        }

    }

    Scaffold(


        snackbarHost = {

            SnackbarHost(
                hostState = snackBarHostState
            )

        }

    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Login"
            )
            Spacer(
                modifier = Modifier.height(24.dp)
            )

            var emailError by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = !android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                },
                label = { Text("Email") },
                isError = emailError,
                supportingText = {
                    if (emailError) {
                        Text("Please enter a valid email")
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )
            PasswordField(
                value = password,
                onValueChange = {
                    password = it
                },
                label = "Password",
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )


            PrimaryButton(
                text = "Login",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (
                        email.isNotBlank()&&
                        password.isNotBlank()
                    ){
                        viewModel.login(email,password)
                    }
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            TextButton(
                onClick = {
                    navController.navigate(
                        Routes.Register.route
                    )
                }
            ) {
                Text(
                    text = "Don't have an account? Create one"
                )
            }
        }
    }



}