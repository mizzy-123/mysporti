package com.manifestasi.mysporty.ui.component.bottomnav

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.manifestasi.mysporty.ui.screen.main.BottomScreens
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.MySportyTheme

@SuppressLint("RestrictedApi")
@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    currentDestination: NavDestination?
){
    val topLevelRoutes = listOf(
        BottomScreens.Home,
        BottomScreens.Profile
    )

    NavigationBar(
        modifier = Modifier
            .drawBehind {
                val shadowColor = Color(0x22000000) // Warna bayangan
                val shadowHeight = 10.dp.toPx() // Tinggi bayangan
                val gradientBrush = Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent, // Transparan di akhir
                        shadowColor, // Warna solid di awal
                    ),
                    startY = -shadowHeight,
                    endY = 0f
                )
                drawRect(
                    brush = gradientBrush,
                    topLeft = Offset(0f, -shadowHeight),
                    size = Size(size.width, shadowHeight)
                )
            },
        containerColor = Color.White
    ) {
        topLevelRoutes.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route::class.qualifiedName } == true
            NavigationBarItem(
                selected = selected,
                icon = {
                    val ic = if (selected) item.selectedIcon else item.icon
                    if (!selected){
                        Image(
                            painter = painterResource(ic),
                            modifier = Modifier.size(24.dp),
                            contentDescription = item.name
                        )
                    } else {
                        Image(
                            painter = painterResource(ic),
                            modifier = Modifier.width(24.dp).height(31.dp),
                            contentDescription = item.name
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                ),
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }

                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

data class TopLevelRoute<T : Any>(val name: String, val route: T, val icon: Int, val selectedIcon: Int)

@Composable
@Preview(showBackground = true)
fun BottomNavigationBarPreview(){
    val navController: NavHostController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    MySportyTheme {
        BottomNavigationBar(
            navController = navController,
            currentDestination = currentDestination
        )
    }
}