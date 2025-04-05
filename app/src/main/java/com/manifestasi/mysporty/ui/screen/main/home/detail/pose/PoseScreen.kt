package com.manifestasi.mysporty.ui.screen.main.home.detail.pose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.manifestasi.mysporty.Pose
import com.manifestasi.mysporty.ui.component.CameraPreview
import com.manifestasi.mysporty.ui.component.DrawPoseLandmarks
import com.manifestasi.mysporty.ui.component.PoseResult
import com.manifestasi.mysporty.ui.theme.MySportyTheme

@Composable
fun PoseScreen(
    dataPose: Pose
){
    val context = LocalContext.current
    var landmarks by remember { mutableStateOf<List<NormalizedLandmark>>(emptyList()) }
    val poseAnalyzer = remember { PoseAnalyzer(context) { detectedLandmarks ->
        landmarks = detectedLandmarks
    } }

    var predictedClass by remember { mutableStateOf(-1) }

    DisposableEffect(Unit) {
        onDispose {
            poseAnalyzer.release() // Release resource saat Composable dihancurkan
            poseAnalyzer.excersiseClassifyClose()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(onFrame = { imageProxy ->
            poseAnalyzer.analyze(imageProxy)
            poseAnalyzer.excersiseClassify(landmarks) { predicted ->
                predictedClass = predicted
            }
        })

        DrawPoseLandmarks(landmarks = landmarks, modifier = Modifier.fillMaxSize())
        PoseResult(
            dataPose = dataPose,
            result = predictedClass
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PoseScreenPreview(){
    MySportyTheme {
        PoseScreen(
            dataPose = Pose(
                id = "",
                repetition = 9,
                link = "",
                start = "",
                start_state = "",
                name = ""
            )
        )
    }
}