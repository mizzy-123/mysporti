package com.manifestasi.mysporty.ui.component.card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.ui.theme.BlueColor1
import com.manifestasi.mysporty.ui.theme.BlueColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.Purple1
import com.manifestasi.mysporty.ui.theme.Purple2
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun CardBMI(
    height: Float,
    weight: Float
){
    val bmi = rememberSaveable(height, weight) {
        weight / ((height / 100) * (height / 100))
    }

    val resultBmi = when {
        bmi < 18.5 -> "Underweight"
        bmi in 18.5..24.9 -> "Normal weight"
        bmi in 25.0..29.9 -> "Overweight"
        else -> "Obesity"
    }

    val result = String.format("%.1f", bmi)

    val animatedProgress by animateFloatAsState(targetValue = bmi, label = "progress_animation")

//    val offsetXTextCircleChart = if (animatedProgress <= 20)

    val offsetYTextCircleResult = if (animatedProgress <= 50f) (-20 - (4 - (animatedProgress - 20) * 0.8)) else (-20 - (4 - (50 - 20) * 0.8))
    val offsetXTextCircleResult = if (animatedProgress == 100f) 63f else 80f

    Card (
        modifier = Modifier
            .width(315.dp)
            .height(146.dp)
            .shadow(
                elevation = 25.dp,
                ambientColor = BlueColor1,
                spotColor = BlueColor2,
                shape = RoundedCornerShape(22.dp)
            ),
        shape = RoundedCornerShape(22.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp // Mengatur tinggi shadow
        ),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(brush = Brush.linearGradient(
                    colors = listOf(
                        BlueColor1,
                        BlueColor2
                    ),
                    start = Offset(337f, 158f),
                    end = Offset(-73.891f, 89.4009f)
                ))
        ) {
            Canvas(
                modifier = Modifier
                    .size(50.dp)
                    .alpha(0.3f)
                    .align(Alignment.BottomStart)
                    .offset(x = (-15).dp, y = (15).dp)
            ){
                drawArc(
                    color = Color.White,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = true
                )
            }

            Canvas(
                modifier = Modifier
                    .size(50.dp)
                    .alpha(0.3f)
                    .align(Alignment.BottomEnd)
                    .offset(x = (10).dp, y = (5).dp)
            ){
                drawArc(
                    color = Color.White,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = true
                )
            }

            Canvas(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .alpha(0.3f)
                    .align(Alignment.Center)
            ) {
                drawCircle(
                    color = Color.White,
                    center = Offset(8.dp.toPx(), ((size.minDimension / 2) - 80)), // Geser ke kanan
                    radius = 7.5.dp.toPx() // Setengah dari ukuran sebelumnya
                )

                drawCircle(
                    color = Color.White,
                    center = Offset(60.dp.toPx(), ((size.minDimension / 2) - 30)), // Geser ke kanan
                    radius = 7.5.dp.toPx() // Setengah dari ukuran sebelumnya
                )

                drawCircle(
                    color = Color.White,
                    center = Offset(30.dp.toPx(), ((size.minDimension / 2) + 140)), // Geser ke kanan
                    radius = 7.5.dp.toPx() // Setengah dari ukuran sebelumnya
                )

                drawCircle(
                    color = Color.White,
                    center = Offset((80).dp.toPx(), ((size.minDimension / 2) + 200)), // Geser ke kanan
                    radius = 7.5.dp.toPx() // Setengah dari ukuran sebelumnya
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 26.dp
                    )
            ) {
                Text(
                    text = "BMI (Body Mass Index)",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                )
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "You have a $resultBmi",
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
            }

            Box(Modifier
                .fillMaxHeight()
                .width(250.dp)
                .align(Alignment.CenterEnd)
            ){

                Canvas(modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.Center)
                    .offset(x = 60.dp)
                ) {
                    drawCircle(
                        color = Color.White,
                        radius = 50.dp.toPx()
                    )
                }

                Canvas(modifier = Modifier
                    .size(125.dp)
                    .align(Alignment.Center)
                    .offset(x = 60.dp)
                ) {
                    val startAngle = -90f // Start dari jam 12
                    val sweepAngle = (animatedProgress / 100) * 360 // Hitung sudut berdasarkan persentase

                    drawArc(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Purple1,
                                Purple2
                            ),
                            start = Offset(x = 84f, y = 118f),
                            end = Offset(x = -57.2761f, y = 107.068f)
                        ),
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = true
                    )
                }

                Text(
                    text = result,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(x = offsetXTextCircleResult.dp, y = offsetYTextCircleResult.dp),
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                )
            }

        }
    }
}

@Composable
@Preview(
    showBackground = true
)
fun CardBMIPreview(){
    MySportyTheme {
        CardBMI(
            height = 169f,
            weight = 68f
        )
    }
}