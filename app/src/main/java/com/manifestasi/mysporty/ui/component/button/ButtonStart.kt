package com.manifestasi.mysporty.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.ButtonColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun ButtonStart(
    onClick: () -> Unit,
    height: Dp = 60.dp
){
    Button(
        modifier = Modifier.fillMaxWidth().height(height),
        onClick = onClick,
        shape = RoundedCornerShape(99.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        elevation = ButtonDefaults.buttonElevation(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            ButtonColor1,
                            ButtonColor2
                        ),
                        start = Offset(x = 337f, y = 72f),
                        end = Offset(x = -25.5147f, y = -75.2716f)
                    )
                )
        ){
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Start",
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonStartPreview(){
    MySportyTheme {
        ButtonStart(
            onClick = {}
        )
    }
}