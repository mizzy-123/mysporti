package com.manifestasi.mysporty.ui.screen.main.home.detail.pose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.manifestasi.mysporty.Pose
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.model.DataExercise
import com.manifestasi.mysporty.ui.component.CameraPreview
import com.manifestasi.mysporty.ui.component.DrawPoseLandmarks
import com.manifestasi.mysporty.ui.component.PoseResult
import com.manifestasi.mysporty.ui.component.dialog.StartDialog
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.util.Excersise

@Composable
fun PoseScreen(
    dataPose: Pose,
    poseViewModel: PoseViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit
){
    val context = LocalContext.current
    var landmarks by remember { mutableStateOf<List<NormalizedLandmark>>(emptyList()) }
    var startDialog by rememberSaveable { mutableStateOf(true) }

    val tutorial = remember {
        val exercise = Excersise.getData.find { it.id == dataPose.id }
        if (exercise != null) {
            mutableStateListOf<DataExercise>(exercise)
        } else {
            mutableStateListOf<DataExercise>()
        }
    }

    val getTutorial = tutorial[0]

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

    if (startDialog){
        StartDialog(
            title = "Sebelum memulai",
            image = dataPose.start_position_image,
            description = getTutorial.tutorialList[0].description,
            onDismiss = {},
            buttonOnclick = {
                startDialog = false
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (!startDialog){
            CameraPreview(onFrame = { imageProxy ->
                poseAnalyzer.analyze(imageProxy)
                poseAnalyzer.excersiseClassify(landmarks) { predicted ->
                    predictedClass = predicted
                }
            })

            DrawPoseLandmarks(landmarks = landmarks, modifier = Modifier.fillMaxSize())
            PoseResult(
                context = context,
                dataPose = dataPose,
                result = predictedClass,
                poseViewModel = poseViewModel,
                onNavigateToMain = onNavigateToMain
            )
        }
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
                name = "",
                start_position_image = R.drawable.pushup_start
            ),
            onNavigateToMain = {}
        )
    }
}