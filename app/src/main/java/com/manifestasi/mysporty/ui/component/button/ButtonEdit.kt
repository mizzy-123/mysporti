package com.manifestasi.mysporty.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
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
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.ButtonColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun ButtonEdit(
    onClick: () -> Unit
){
    Button(
        modifier = Modifier.width(83.dp).height(30.dp),
        onClick = onClick,
        shape = RoundedCornerShape(99.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            ButtonColor1,
                            ButtonColor2
                        ),
                        start = Offset(x = 83f, y = 30f),
                        end = Offset(x = -23.4072f, y = 7.21957f)
                    )
                )
        ){
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Edit",
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonEditPreview(){
    MySportyTheme {
        ButtonEdit(
            onClick = {}
        )
    }
}