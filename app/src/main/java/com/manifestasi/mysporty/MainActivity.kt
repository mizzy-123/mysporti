package com.manifestasi.mysporty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import com.manifestasi.mysporty.ui.component.CameraPreview
import com.manifestasi.mysporty.ui.component.DrawPoseLandmarks
import com.manifestasi.mysporty.ui.component.PoseAnalyzer
import com.manifestasi.mysporty.ui.component.PoseResult
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MySportyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RealTimePoseDetection()
                }
            }
        }
    }
}

@Composable
fun RealTimePoseDetection() {
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
        PoseResult(predictedClass)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MySportyTheme {
        Greeting("Android")
    }
}