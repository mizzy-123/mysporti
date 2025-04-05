package com.manifestasi.mysporty.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.ButtonColor2
import com.manifestasi.mysporty.ui.theme.GrayColor1
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun CardInformation(
    height: Int,
    weight: Int,
    age: Int
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth().height(65.dp).weight(1f),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${height}cm",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                ButtonColor2,
                                ButtonColor1
                            )
                        )
                    )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Height",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 12.sp,
                        color = GrayColor1
                    )
                )
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth().height(65.dp).weight(1f),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${weight}kg",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                ButtonColor2,
                                ButtonColor1
                            )
                        )
                    )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Weight",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 12.sp,
                        color = GrayColor1
                    )
                )
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth().height(65.dp).weight(1f),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${age}yo",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                ButtonColor2,
                                ButtonColor1
                            )
                        )
                    )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "Age",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 12.sp,
                        color = GrayColor1
                    )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardInformationPreview(){
    MySportyTheme {
        CardInformation(
            height = 20,
            weight = 10,
            age = 20
        )
    }
}