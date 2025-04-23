package com.manifestasi.mysporty.ui.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manifestasi.mysporty.ui.component.button.ButtonLogin
import com.manifestasi.mysporty.ui.component.divider.DividerAuth
import com.manifestasi.mysporty.ui.component.field.InputEmailField
import com.manifestasi.mysporty.ui.component.field.InputPasswordField
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.TextColor2
import com.manifestasi.mysporty.ui.theme.TextColor3
import com.manifestasi.mysporty.ui.theme.poppins
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun LoginScreen(
    onNavigateToRegister: () -> Unit,
    onNavigateToMain: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
){

    val email = viewModel.email.collectAsState(initial = "")
    val password = viewModel.password.collectAsState()
    val isLoading = viewModel.loadingLogin.collectAsState()
    var visiblePassword by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loginSuccess.collect { result ->
            if (result){
                onNavigateToMain()
                viewModel.resetLoginSuccess()
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Spacer(Modifier.height(40.dp))

            Text(
                "Hey There,",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins
                )
            )

            Text(
                "Welcome Back",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(30.dp))

            InputEmailField(
                text = email.value,
                placeholder = "Email",
                onChange = {
                    viewModel.onEmailChange(it)
                }
            )

            Spacer(Modifier.height(15.dp))

            InputPasswordField(
                text = password.value,
                placeholder = "Password",
                onChange = {
                    viewModel.onPasswordChange(it)
                },
                isPasswordVisible = visiblePassword,
                onVisibilityChange = {
                    visiblePassword = it
                }
            )

            Spacer(Modifier.height(100.dp))

            ButtonLogin(
                onClick = {
                    viewModel.login()
                },
                isDisabled = isLoading.value,
                isLoading = isLoading.value
            )

            Spacer(Modifier.height(29.dp))

            DividerAuth()

            Spacer(Modifier.height(29.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account yet?",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 14.sp
                    )
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    modifier = Modifier.clickable {
                        onNavigateToRegister()
                    },
                    text = "Register",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 14.sp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                TextColor2,
                                TextColor3
                            ),
                            start = Offset(0f, 0f), // Mulai dari kiri atas
                            end = Offset(
                                x = 300f * cos(Math.toRadians(120.0)).toFloat(),  // Hitung x berdasarkan sudut
                                y = 300f * sin(Math.toRadians(120.0)).toFloat()  // Hitung y berdasarkan sudut
                            )
                        )
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview(){
    MySportyTheme {
        LoginScreen(
            onNavigateToRegister = {},
            onNavigateToMain = {}
        )
    }
}