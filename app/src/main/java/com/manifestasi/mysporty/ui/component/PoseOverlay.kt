package com.manifestasi.mysporty.ui.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark

@Composable
fun PoseOverlay(
    landmarks: List<NormalizedLandmark>,
    connections: List<Pair<Int, Int>>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()){
        // Gambar koneksi antar landmark
        for (connection in connections) {
            val startLandmark = landmarks[connection.first]
            val endLandmark = landmarks[connection.second]

            val startX = startLandmark.x() * size.width
            val startY = startLandmark.y() * size.height
            val endX = endLandmark.x() * size.width
            val endY = endLandmark.y() * size.height

            drawLine(
                color = Color.Green,
                start = Offset(startX, startY),
                end = Offset(endX, endY),
                strokeWidth = 5f
            )
        }

        // Gambar landmark
        for (landmark in landmarks) {
            val x = landmark.x() * size.width
            val y = landmark.y() * size.height

            drawCircle(
                color = Color.Red,
                radius = 10f,
                center = Offset(x, y)
            )
        }
    }
}