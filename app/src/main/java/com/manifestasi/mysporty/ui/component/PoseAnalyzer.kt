package com.manifestasi.mysporty.ui.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageFormat
import android.graphics.Matrix
import android.graphics.Rect
import android.graphics.YuvImage
import android.media.Image
import android.os.SystemClock
import android.util.Log
import androidx.annotation.OptIn
import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.ImageProcessingOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import java.io.ByteArrayOutputStream

class PoseAnalyzer(
    context: Context,
    private val onPoseDetected: (List<NormalizedLandmark>) -> Unit
) : ImageAnalysis.Analyzer {

    private val poseLandmarker: PoseLandmarker

    private var frameCounter = 0

    init {
        val modelPath = "pose_landmarker_full.task"
        val baseOptions = BaseOptions.builder()
            .setModelAssetPath(modelPath)
            .build()

        val options = PoseLandmarker.PoseLandmarkerOptions.builder()
            .setBaseOptions(baseOptions)
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setResultListener { result, _ ->
                if (result != null && result.landmarks().isNotEmpty()) {
                    onPoseDetected(result.landmarks().first())
                }
            }
            .setMinPoseDetectionConfidence(0.5f)
            .setMinTrackingConfidence(0.5f)
            .build()

        poseLandmarker = PoseLandmarker.createFromOptions(context, options)
    }

    @OptIn(ExperimentalGetImage::class)
    override fun analyze(imageProxy: ImageProxy) {
        try {
//            val mediaImage = imageProxy.image ?: return
//            val mpImage = BitmapImageBuilder(mediaImage.toBitmap()).build()

            // Langsung panggil detectAsync tanpa perlu memeriksa error manual
//            poseLandmarker.detectAsync(mpImage, SystemClock.uptimeMillis())

            frameCounter++
            if (frameCounter % 3 != 0) { // Analisis hanya setiap 3 frame
                return
            }

            val mediaImage = imageProxy.image ?: return
            val rotationDegrees = imageProxy.imageInfo.rotationDegrees // Dapatkan derajat rotasi gambar

            // Konversi Image ke Bitmap dengan rotasi yang benar
            val bitmap = mediaImage.toBitmap(rotationDegrees)

            // Proses bitmap dengan MediaPipe
            val mpImage = BitmapImageBuilder(bitmap).build()
            poseLandmarker.detectAsync(mpImage, SystemClock.uptimeMillis())

        } catch (e: Exception) {
            e.printStackTrace() // Log error untuk debugging
        } finally {
            imageProxy.close() // Pindahkan close() ke finally untuk memastikan tetap tertutup
        }
    }

    private fun Image.toBitmap(rotationDegrees: Int): Bitmap {
        val yBuffer = planes[0].buffer
        val uBuffer = planes[1].buffer
        val vBuffer = planes[2].buffer

        val ySize = yBuffer.remaining()
        val uSize = uBuffer.remaining()
        val vSize = vBuffer.remaining()

        val nv21 = ByteArray(ySize + uSize + vSize)
        yBuffer.get(nv21, 0, ySize)
        vBuffer.get(nv21, ySize, vSize)
        uBuffer.get(nv21, ySize + vSize, uSize)

        val yuvImage = YuvImage(nv21, ImageFormat.NV21, width, height, null)
        val out = ByteArrayOutputStream()
        yuvImage.compressToJpeg(Rect(0, 0, width, height), 100, out)
        val byteArray = out.toByteArray()
        out.close()

        // Decode byte array ke Bitmap
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)

        // Putar bitmap sesuai rotasi kamera
        val matrix = Matrix().apply {
            postRotate(rotationDegrees.toFloat())
        }
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    fun release(){
        poseLandmarker.close()
    }
}

