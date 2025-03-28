package com.manifestasi.mysporty.ui.screen.register

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
import com.manifestasi.mysporty.ui.component.button.ButtonRegister
import com.manifestasi.mysporty.ui.component.divider.DividerAuth
import com.manifestasi.mysporty.ui.component.field.InputEmailField
import com.manifestasi.mysporty.ui.component.field.InputNameField
import com.manifestasi.mysporty.ui.component.field.InputPasswordField
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.TextColor2
import com.manifestasi.mysporty.ui.theme.TextColor3
import com.manifestasi.mysporty.ui.theme.poppins
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit
){

    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var visiblePassword by rememberSaveable { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(Modifier.height(40.dp))

            Text(
                "Hey There,",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = poppins
                )
            )

            Text(
                "Create an Account",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = poppins,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(30.dp))

            InputNameField(
                text = firstName,
                placeholder = "First Name",
                onChange = {
                    firstName = it
                }
            )

            Spacer(Modifier.height(15.dp))

            InputNameField(
                text = lastName,
                placeholder = "Last Name",
                onChange = {
                    lastName = it
                }
            )

            Spacer(Modifier.height(15.dp))

            InputEmailField(
                text = email,
                placeholder = "Email",
                onChange = {
                    email = it
                }
            )

            Spacer(Modifier.height(15.dp))

            InputPasswordField(
                text = password,
                placeholder = "Password",
                onChange = {
                    password = it
                },
                isPasswordVisible = visiblePassword,
                onVisibilityChange = {
                    visiblePassword = it
                }
            )

            Spacer(Modifier.height(100.dp))

            ButtonRegister(onClick = {

            })

            Spacer(Modifier.height(29.dp))

            DividerAuth()

            Spacer(Modifier.height(29.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have an account?",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 14.sp
                    )
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    modifier = Modifier.clickable {
                        onNavigateToLogin()
                    },
                    text = "Login",
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
@Preview(
    showBackground = true,
    showSystemUi = true
)
fun RegisterScreenPreview(){
    MySportyTheme {
        RegisterScreen(
            onNavigateToLogin = {}
        )
    }
}