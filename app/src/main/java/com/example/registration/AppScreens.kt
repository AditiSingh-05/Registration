package com.example.registration

sealed class AppScreens(val route : String) {
    object SignupPage : AppScreens("signup_page")
    object LoginPage : AppScreens("login_page")  // Corrected
    object HomePage : AppScreens("home_page")    // Corrected




}