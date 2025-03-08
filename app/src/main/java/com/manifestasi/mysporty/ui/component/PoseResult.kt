package com.manifestasi.mysporty.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.util.label

@Composable
fun PoseResult(result: Int){
    Box(modifier = Modifier.fillMaxSize()){
        Text(
            "Hasil: ${if (result != -1) label[result] else result}",
            modifier = Modifier.align(Alignment.TopStart),
            style = TextStyle(
                fontSize = 20.sp,
                color = Color.Red
            )
        )
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