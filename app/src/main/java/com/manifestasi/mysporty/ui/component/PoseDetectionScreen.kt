package com.manifestasi.mysporty.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.manifestasi.mysporty.MainViewModel
import com.manifestasi.mysporty.util.connections

@Composable
fun PoseDetectionScreen(mainViewModel: MainViewModel = hiltViewModel()) {
    val poseLandmarkerResult by mainViewModel.poseLandmarkerResult

    Box(modifier = Modifier.fillMaxSize()) {

        // Gambar landmark dan koneksi
        poseLandmarkerResult?.let { result ->
            val landmarks = result.landmarks()[0]
            PoseOverlay(
                landmarks = landmarks,
                connections = connections,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Tampilkan hasil prediksi
//        predictedPose?.let { pose ->
//            Text(
//                text = "Predicted Pose: $pose",
//                color = Color.White,
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .padding(16.dp)
//            )
//        }
    }
}