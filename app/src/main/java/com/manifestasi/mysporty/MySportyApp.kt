package com.manifestasi.mysporty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.data.model.Profile
import com.manifestasi.mysporty.data.model.Tutorial
import com.manifestasi.mysporty.ui.screen.IsLoginViewModel
import com.manifestasi.mysporty.ui.screen.login.LoginScreen
import com.manifestasi.mysporty.ui.screen.main.MainScreen
import com.manifestasi.mysporty.ui.screen.main.home.detail.DetailScreen
import com.manifestasi.mysporty.ui.screen.main.home.detail.pose.PoseScreen
import com.manifestasi.mysporty.ui.screen.main.profile.history.HistoryScreen
import com.manifestasi.mysporty.ui.screen.main.profile.personaldata.PersonalDataScreen
import com.manifestasi.mysporty.ui.screen.register.RegisterScreen
import com.manifestasi.mysporty.ui.screen.splash.SplashScreen
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Composable
fun MySportyApp(
    navController: NavHostController = rememberNavController(),
){

    NavHost(
        navController = navController,
        startDestination = Splash
    ) {

        composable<Splash> {
            SplashScreen(
                onNavigateToMain = {
                    navController.navigate(Main){
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(Login){
                        popUpTo(0) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

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
                        start_position_image = detail.start_position_image,
                        start_state = detail.start_state,
                        link = detail.link,
                        repetition = repetitionFix
                    ))
                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<Pose> { backStackEntry ->
            val pose = backStackEntry.toRoute<Pose>()
            PoseScreen(
                dataPose = pose,
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

        composable<History> {
            HistoryScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable<PersonalData> { backStackEntry ->
            val personalData = backStackEntry.toRoute<PersonalData>()

            PersonalDataScreen(
                profile = Profile(
                    user_id = personalData.user_id,
                    first_name = personalData.first_name,
                    last_name = personalData.last_name,
                    height = personalData.height,
                    weight = personalData.weight,
                    age = personalData.age
                ),
                onNavigateBack = {
                    navController.popBackStack()
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
    }
}

@Serializable
object Splash

@Serializable
object Login

@Serializable
object Register

@Serializable
object Main

@Serializable
object History

@Serializable
data class Detail(
    val id: String,
    val name: String,
    val start: String,
    val start_state: String,
    val start_position_image: Int,
    val description: String,
    val link: String,
    val repetition: Int
)

@Serializable
data class Pose(
    val id: String,
    val name: String,
    val start: String,
    val start_position_image: Int,
    val start_state: String,
    val link: String,
    val repetition: Int
)

@Serializable
data class PersonalData(
    val user_id: String,
    val first_name: String,
    val last_name: String,
    val height: Int? = null,
    val weight: Int? = null,
    val age: Int? = null
)