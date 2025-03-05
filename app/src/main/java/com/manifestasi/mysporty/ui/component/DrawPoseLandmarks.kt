package com.manifestasi.mysporty.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark

@Composable
fun DrawPoseLandmarks(landmarks: List<NormalizedLandmark>, modifier: Modifier = Modifier) {
    // Definisi koneksi pose (indeks landmark yang akan dihubungkan)
    val poseConnections = listOf(
        Pair(11, 12), // Bahu kiri ke bahu kanan
        Pair(11, 13), // Bahu kiri ke siku kiri
        Pair(13, 15), // Siku kiri ke pergelangan tangan kiri
        Pair(12, 14), // Bahu kanan ke siku kanan
        Pair(14, 16), // Siku kanan ke pergelangan tangan kanan
        Pair(11, 23), // Bahu kiri ke pinggul kiri
        Pair(12, 24), // Bahu kanan ke pinggul kanan
        Pair(23, 24), // Pinggul kiri ke pinggul kanan
        Pair(23, 25), // Pinggul kiri ke lutut kiri
        Pair(24, 26), // Pinggul kanan ke lutut kanan
        Pair(25, 27), // Lutut kiri ke pergelangan kaki kiri
        Pair(26, 28)  // Lutut kanan ke pergelangan kaki kanan
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        // Gambar lingkaran untuk setiap landmark
        landmarks.forEach { landmark ->
            drawCircle(
                color = Color.Red,
                radius = 8f,
                center = Offset(
                    landmark.x() * size.width,
                    landmark.y() * size.height
                )
            )
        }

        // Gambar garis antara landmark yang terhubung
        poseConnections.forEach { (startIndex, endIndex) ->
            val startLandmark = landmarks.getOrNull(startIndex)
            val endLandmark = landmarks.getOrNull(endIndex)

            if (startLandmark != null && endLandmark != null) {
                drawLine(
                    color = Color.Blue,
                    start = Offset(startLandmark.x() * size.width, startLandmark.y() * size.height),
                    end = Offset(endLandmark.x() * size.width, endLandmark.y() * size.height),
                    strokeWidth = 4f
                )
            }
        }
    }
}
