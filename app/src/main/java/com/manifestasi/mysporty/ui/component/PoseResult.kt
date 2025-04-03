package com.manifestasi.mysporty.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.util.label
import kotlinx.coroutines.delay

@Composable
fun PoseResult(result: Int){

    var repetitionCount by rememberSaveable { mutableStateOf(0) }
    val prediction: String = if (result != -1) label[result] else result.toString()
    var timeLeft by remember { mutableStateOf(3) }

    var isStart by rememberSaveable { mutableStateOf(false) }

    var currentState by rememberSaveable { mutableStateOf("") }

    var messageInstruction by rememberSaveable { mutableStateOf("Ambil posisi") }

    LaunchedEffect(prediction, timeLeft) {
        if (prediction == "pushup_up" && timeLeft > 0){
            messageInstruction = "Bagus pertahankan"
            delay(1000L)
            timeLeft--
        } else {
            messageInstruction = "Ambil posisi"
        }

        if (timeLeft == 0) {
            currentState = "up"
            isStart = true
        }
    }

    LaunchedEffect(prediction) {
        if (isStart) {
            if (prediction == "pushup_down" && currentState == "up") {
                delay(500) // Tambahkan delay untuk memastikan deteksi stabil
                currentState = "down"
                repetitionCount++
            } else if (prediction == "pushup_up" && currentState == "down") {
                delay(500) // Tambahkan delay agar transisi tidak terlalu cepat
                currentState = "up"
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()){
        Text(
            "Hasil: $prediction",
            modifier = Modifier.align(Alignment.TopStart),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Red
            )
        )

        if (isStart){
            Text(
                repetitionCount.toString(),
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 40.sp,
                    color = Color.Red
                )
            )
        } else {
            Text(
                messageInstruction,
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 40.sp,
                    color = Color.Red
                )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PoseResultPreview(){
    MySportyTheme {
        PoseResult(
            result = 0
        )
    }
}