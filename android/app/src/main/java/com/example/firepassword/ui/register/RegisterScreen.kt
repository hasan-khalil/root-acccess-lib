package com.example.firepassword.ui.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.firepassword.data.remote.FirebaseSource
import com.example.firepassword.data.repository.AuthRepositoryImpl
import com.example.firepassword.ui.navigation.Routes
import com.example.firepassword.ui.widgets.AppTextField
import com.example.firepassword.ui.widgets.PasswordField
import com.example.firepassword.ui.widgets.PrimaryButton
import com.example.firepassword.utils.Resource

@Composable fun RegisterScreen( navController: NavController ) {

    var username by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    val repository = remember {
        AuthRepositoryImpl(
            FirebaseSource()
        )
    }



    val viewModel: RegisterViewModel = viewModel(
        factory = RegisterViewModel.Factory(repository)
    )
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state) {

        when (state) {

            is Resource.Success -> {

                navController.navigate(
                    Routes.Home.route
                ) {

                    popUpTo(
                        Routes.Register.route
                    ) {
                        inclusive = true
                    }

                }

            }

            else -> {}
        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Create Account"
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        AppTextField(
            value = username,
            onValueChange = { username = it},
            label = "Username",
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
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
            modifier = Modifier.height(12.dp)
        )


        PasswordField(
            value = password,
            onValueChange = { password = it},
            label = "Password",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        PasswordField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it},
            label = "Confirm Password",
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        PrimaryButton(
            text = if (state is Resource.Loading)
                "Creating..."
            else
                "Create Account",
            onClick = {
                if (
                    username.isNotBlank() &&
                    email.isNotBlank() &&
                    password.isNotBlank() &&
                    password == confirmPassword
                ){
                    viewModel.register(
                        username = username,
                        email = email,
                        password = password
                    )
                }
            },
            enabled = state !is Resource.Loading,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        TextButton(
            onClick = {
                navController.navigate(
                    Routes.Login.route
                )
            }
        ) {
            Text(
                text = "Already have an account? Login"
            )
        }
    }

}