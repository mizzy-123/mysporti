package com.manifestasi.mysporty.ui.screen.main

import com.manifestasi.mysporty.R
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomScreens<T>(val name: String, val route: T, val icon: Int, val selectedIcon: Int) {
    @Serializable
    data object Home: BottomScreens<HomeRoute>("Home", HomeRoute, R.drawable.home, R.drawable.home_active2)

    @Serializable
    data object Profile: BottomScreens<ProfileRoute>("Profile", ProfileRoute, R.drawable.profile, R.drawable.profile_active2)
}