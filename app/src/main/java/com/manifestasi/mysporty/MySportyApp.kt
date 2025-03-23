package com.manifestasi.mysporty

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.manifestasi.mysporty.ui.screen.login.LoginScreen
import com.manifestasi.mysporty.ui.screen.main.MainScreen
import com.manifestasi.mysporty.ui.screen.main.home.detail.DetailScreen
import com.manifestasi.mysporty.ui.screen.register.RegisterScreen
import kotlinx.serialization.Serializable

@Composable
fun MySportyApp(
    navController: NavHostController = rememberNavController()
){
    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(
                onNavigateToRegister = {
                    navController.navigate(Register)
                },
                onNavigateToMain = {
                    navController.navigate(Main){
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
        composable<Register> {
            RegisterScreen(
                onNavigateToLogin = {
                    navController.navigate(Login)
                }
            )
        }

        composable<Main> {
            MainScreen()
        }

        composable<Detail> {
            DetailScreen()
        }
    }
}

@Serializable
object Login

@Serializable
object Register

@Serializable
object Main

@Serializable
object Detail