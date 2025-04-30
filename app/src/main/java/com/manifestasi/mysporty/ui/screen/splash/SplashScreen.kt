package com.manifestasi.mysporty.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.manifestasi.mysporty.Main
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.Resource
import com.manifestasi.mysporty.ui.screen.IsLoginViewModel
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.ButtonColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme

@Composable
fun SplashScreen(
    onNavigateToMain: () -> Unit,
    onNavigateToLogin: () -> Unit,
    isLoginViewModel: IsLoginViewModel = hiltViewModel()
){

    LaunchedEffect(Unit) {
        isLoginViewModel.isLoggedIn().collect { event ->
            when (event){
                is Resource.Success -> {
                    if (event.data){
                        onNavigateToMain()
                    }
                } else -> {
                    onNavigateToLogin()
                }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        ButtonColor1,
                        ButtonColor2
                    ),
                    start = Offset(x = 375f, y = 812f),
                    end = Offset(x = -127.149f, y = 794.055f)
                )
            )
    ){
        Image(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(R.drawable.logo),
            contentDescription = "logo"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    MySportyTheme {
        SplashScreen(
            onNavigateToMain = {},
            onNavigateToLogin = {}
        )
    }
}