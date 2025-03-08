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
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import com.manifestasi.mysporty.ml.ExcerciseClassifier
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder

class PoseAnalyzer(
    context: Context,
    private val onPoseDetected: (List<NormalizedLandmark>) -> Unit
) : ImageAnalysis.Analyzer {

    private val poseLandmarker: PoseLandmarker

    private var frameCounter = 0

    private val model = ExcerciseClassifier.newInstance(context)

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

    fun excersiseClassify(landmark: List<NormalizedLandmark>, predicted: (Int) -> Unit){
        val xCoordinates = mutableListOf<Float>()
        val yCoordinates = mutableListOf<Float>()
        val zCoordinates = mutableListOf<Float>()

        for (l in landmark){
            xCoordinates.add(l.x())
            yCoordinates.add(l.y())
            zCoordinates.add(l.z())
        }

        val data = xCoordinates + yCoordinates + zCoordinates
        val floatArray = data.toFloatArray()

        if (floatArray.isEmpty()) {
            Log.e("PoseAnalyzer", "Float array is empty! Skipping classification.")
            return
        }

        val byteBuffer = ByteBuffer.allocateDirect(floatArray.size * 4)
        byteBuffer.order(ByteOrder.nativeOrder())

        for (value in floatArray) {
            byteBuffer.putFloat(value)
        }

        byteBuffer.rewind() // Tambahkan ini untuk memastikan buffer dibaca dari awal

        val inputFeature0 = TensorBuffer.createFixedSize(intArrayOf(1, floatArray.size), DataType.FLOAT32)
        inputFeature0.loadBuffer(byteBuffer)

        val outputs = model.process(inputFeature0)
        val outputFeature0 = outputs.outputFeature0AsTensorBuffer

        val predictedClass = outputFeature0.floatArray
            .withIndex()
            .maxByOrNull { it.value }
            ?.index ?: -1

        predicted(predictedClass)
    }

    fun excersiseClassifyClose(){
        model.close()
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

