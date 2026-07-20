package com.example.firepassword.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.firepassword.ui.home.HomeScreen
import com.example.firepassword.ui.login.LoginScreen
import com.example.firepassword.ui.register.RegisterScreen
import java.io.File

@Composable
fun NavigationGraph( navController: NavHostController , context: Context) {
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {
        composable( Routes.Login.route ) {
            LoginScreen( navController = navController )
        }
        composable( Routes.Register.route ) {
            RegisterScreen( navController = navController )
        }
        composable( Routes.Home.route ) {
            HomeScreen()
        }
    }
}

