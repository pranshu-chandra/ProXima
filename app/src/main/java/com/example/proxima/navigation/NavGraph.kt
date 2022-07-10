package com.example.proxima.navigation

import android.window.SplashScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import coil.annotation.ExperimentalCoilApi
import com.example.proxima.Presentation.Screens.MainScreen
import com.example.proxima.Presentation.Screens.login.LoginScreen
import com.example.proxima.Presentation.Screens.registration.RegistrationPage
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalCoilApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
@ExperimentalMaterialApi
fun SetupNavGraph(navController: NavHostController){

    NavHost(navController = navController,
    startDestination =Screen.Login.route
    ){
        composable(route = Screen.Login.route){
        LoginScreen(navController = navController)
        }

        composable(route = Screen.MainScreen.route){
            MainScreen(navController = navController)
        }
        composable(route = Screen.Register.route){
           RegistrationPage(navController = navController)
        }


    }

}