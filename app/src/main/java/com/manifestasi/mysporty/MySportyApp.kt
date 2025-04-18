package com.manifestasi.mysporty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.manifestasi.mysporty.data.model.Tutorial
import com.manifestasi.mysporty.ui.screen.login.LoginScreen
import com.manifestasi.mysporty.ui.screen.main.MainScreen
import com.manifestasi.mysporty.ui.screen.main.home.detail.DetailScreen
import com.manifestasi.mysporty.ui.screen.main.home.detail.pose.PoseScreen
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
            MainScreen(
                rootNavController = navController
            )
        }

        composable<Detail> { backStackEntry ->
            val detail = backStackEntry.toRoute<Detail>()
            var repetitionFix by rememberSaveable { mutableIntStateOf(detail.repetition) }
            DetailScreen(
                dataDetail = detail,
                onSelectedRepetition = {
                    repetitionFix = it
                },
                onNavigateToPose = {
                    navController.navigate(Pose(
                        id = detail.id,
                        name = detail.name,
                        start = detail.start,
                        start_state = detail.start_state,
                        link = detail.link,
                        repetition = repetitionFix
                    ))
                }
            )
        }

        composable<Pose> { backStackEntry ->
            val pose = backStackEntry.toRoute<Pose>()
            PoseScreen(
                dataPose = pose
            )
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
data class Detail(
    val id: String,
    val name: String,
    val start: String,
    val start_state: String,
    val description: String,
    val link: String,
    val repetition: Int
)

@Serializable
data class Pose(
    val id: String,
    val name: String,
    val start: String,
    val start_state: String,
    val link: String,
    val repetition: Int
)