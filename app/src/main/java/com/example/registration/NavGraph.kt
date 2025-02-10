package com.example.registration

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.registration.screens.HomePage
import com.example.registration.screens.LoginPage
import com.example.registration.screens.SignupPage

@Composable
fun NavGraph(navController: NavController,authViewModel: AuthViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SignupPage.route){
        composable(AppScreens.SignupPage.route){
            SignupPage(navController,authViewModel)
        }
        composable(AppScreens.LoginPage.route){
            LoginPage(navController,authViewModel)
        }
        composable(AppScreens.HomePage.route){
            HomePage(navController,authViewModel)
        }

    }
}