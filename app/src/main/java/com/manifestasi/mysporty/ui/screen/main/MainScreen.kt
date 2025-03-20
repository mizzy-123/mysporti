package com.manifestasi.mysporty.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.manifestasi.mysporty.ui.component.bottomnav.BottomNavigationBar
import com.manifestasi.mysporty.ui.screen.main.home.HomeScreen
import com.manifestasi.mysporty.ui.screen.main.profile.ProfileScreen
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import kotlinx.serialization.Serializable

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController()
){

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize().background(Color.White),
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                currentDestination = currentDestination
            )
        }
    ) { innerPadding ->
        val padding = innerPadding

        NavHost(navController, startDestination = HomeRoute, Modifier.padding(padding)){
            composable<HomeRoute> {
                HomeScreen()
            }

            composable<ProfileRoute> {
                ProfileScreen()
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MainScreenPreview(){
    MySportyTheme {
        MainScreen()
    }
}

/* Define route */
@Serializable
object HomeRoute

@Serializable
object ProfileRoute