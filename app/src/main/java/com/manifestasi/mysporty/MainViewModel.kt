package com.manifestasi.mysporty

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.graphics.Bitmap
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.google.mediapipe.framework.image.BitmapImageBuilder
import com.google.mediapipe.tasks.core.BaseOptions
import com.google.mediapipe.tasks.vision.core.RunningMode
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarker.PoseLandmarkerOptions
import com.google.mediapipe.tasks.vision.poselandmarker.PoseLandmarkerResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(@ApplicationContext ctx: Context): ViewModel() {
//    @SuppressLint("StaticFieldLeak")
//    private val context = application.applicationContext

    val poseLandmarkerResult = mutableStateOf<PoseLandmarkerResult?>(null)

    private val poseLandmarker = PoseLandmarker.createFromOptions(
        ctx,
        PoseLandmarkerOptions.builder()
            .setBaseOptions(BaseOptions.builder().setModelAssetPath("pose_landmarker_full.task").build())
            .setRunningMode(RunningMode.LIVE_STREAM)
            .setResultListener { result, input ->
                poseLandmarkerResult.value = result
            }
            .build()
    )

    // Fungsi untuk memproses frame kamera
    fun processFrame(frame: Bitmap) {
        val mpImage = BitmapImageBuilder(frame).build()
        poseLandmarker.detectAsync(mpImage, System.currentTimeMillis())
    }

    override fun onCleared() {
        super.onCleared()
        poseLandmarker.close()
    }
}