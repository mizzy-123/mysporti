package com.manifestasi.mysporty.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.ButtonColor2
import com.manifestasi.mysporty.ui.theme.GrayColor1
import com.manifestasi.mysporty.ui.theme.GrayColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun ButtonLogin(
    onClick: () -> Unit,
    height: Dp = 60.dp,
    isDisabled: Boolean = false,
    isLoading: Boolean = false
){
    val widthGradient = 300f  // Lebar area gradasi
    val heightGradient = 300f // Tinggi area gradasi

    Button(
        modifier = Modifier.fillMaxWidth().height(height),
        onClick = onClick,
        shape = RoundedCornerShape(99.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        elevation = ButtonDefaults.buttonElevation(10.dp),
        enabled = !isDisabled
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = if (isDisabled){
                            listOf(
                                GrayColor1,
                                GrayColor2
                            )
                        } else {
                            listOf(
                                ButtonColor1,
                                ButtonColor2
                            )
                        },
                        start = Offset(0f, 0f), // Mulai dari kiri atas
                        end = Offset(
                            x = widthGradient * cos(Math.toRadians(120.0)).toFloat(),  // Hitung x berdasarkan sudut
                            y = heightGradient * sin(Math.toRadians(120.0)).toFloat()  // Hitung y berdasarkan sudut
                        )
                    ),
                )
        ) {
            if(isLoading){
                CircularProgressIndicator(
                    color = Color.White,
                    strokeWidth = 4.dp,
                    modifier = Modifier
                        .size(30.dp)
                        .align(Alignment.Center)
                )
            } else {
                Row(
                    modifier = Modifier.align(Alignment.Center),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(R.drawable.login), contentDescription = "login")
                    Spacer(Modifier.width((12.5).dp))
                    Text(
                        text = "Login",
                        style = TextStyle(
                            fontFamily = poppins,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonLoginPreview(){
    MySportyTheme {
        ButtonLogin(
            onClick = {}
        )
    }
}