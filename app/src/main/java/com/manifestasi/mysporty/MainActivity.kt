package com.manifestasi.mysporty

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.mediapipe.tasks.components.containers.NormalizedLandmark
import com.manifestasi.mysporty.ui.component.CameraPreview
import com.manifestasi.mysporty.ui.component.DrawPoseLandmarks
import com.manifestasi.mysporty.ui.component.PoseAnalyzer
import com.manifestasi.mysporty.ui.component.PoseDetectionScreen
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.util.toBitmap
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.Executors

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

    DisposableEffect(Unit) {
        onDispose {
            poseAnalyzer.release() // Release resource saat Composable dihancurkan
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPreview(onFrame = { imageProxy ->
            poseAnalyzer.analyze(imageProxy)
        })

        DrawPoseLandmarks(landmarks = landmarks, modifier = Modifier.fillMaxSize())
    }
}


//@Composable
//fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()){
//    CameraPreviewAndAnalysis(mainViewModel)
//}
//
//@OptIn(ExperimentalPermissionsApi::class)
//@Composable
//fun CameraPreviewAndAnalysis(mainViewModel: MainViewModel) {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    // Gunakan CameraX untuk menampilkan pratinjau kamera
//    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
//    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
//
//    // State untuk menyimpan PreviewView
//    var previewView by remember { mutableStateOf<PreviewView?>(null) }
//
//    // Request camera permission
//    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
//
//    LaunchedEffect(Unit) {
//        if (!cameraPermissionState.status.isGranted) {
//            cameraPermissionState.launchPermissionRequest()
//        }
//    }
//
//    // Inisialisasi CameraX
//    LaunchedEffect(previewView) {
//        previewView?.let { previewView ->
//            val cameraProvider = cameraProviderFuture.get()
//            val preview = androidx.camera.core.Preview.Builder()
//                .setTargetResolution(Size(1280, 720))
//                .build()
//            preview.setSurfaceProvider(previewView.surfaceProvider)
//
//            val imageAnalyzer = ImageAnalysis.Builder()
//                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//                .build()
//                .also { analyzer ->
////                    it.setAnalyzer(cameraExecutor, { image ->
////                        val bitmap = image.toBitmap()
////                        mainViewModel.processFrame(bitmap)
////                        image.close()
////                    })
//                    analyzer.setAnalyzer(cameraExecutor) { image ->
//                        try {
//                            val bitmap = image.toBitmap()
//                            bitmap?.let { mainViewModel.processFrame(it) }
//                            Log.d("bitmap", bitmap.toString())
//                        } catch (e: Exception) {
//                            Log.e("ImageAnalyzer", "Error saat memproses frame", e)
//                        } finally {
//                            image.close()
//                        }
//                    }
//                }
//
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    lifecycleOwner, cameraSelector, preview, imageAnalyzer
//                )
//                Log.d("CameraX", "CameraX berhasil diinisialisasi")
//            } catch (exc: Exception) {
//                Log.e("CameraX", "Gagal mengikat use case", exc)
//            }
//        }
//    }
//
//    // Hentikan CameraX saat Composable dihancurkan
//    DisposableEffect(Unit) {
//        onDispose {
//            cameraProviderFuture.get().unbindAll()
//            Log.d("CameraX", "CameraX dihentikan")
//        }
//    }
//
//    // Tampilkan UI
//    if (cameraPermissionState.status.isGranted) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            CameraPreview(
//                onPreviewReady = { previewView = it },
//                modifier = Modifier.fillMaxSize()
//            )
//
//            // Tampilkan hasil deteksi dan prediksi
//            PoseDetectionScreen(mainViewModel)
//        }
//    }
//}

//@Composable
//fun CameraPreviewAndAnalysis(mainViewModel: MainViewModel) {
//    val context = LocalContext.current
//    val lifecycleOwner = LocalLifecycleOwner.current
//
//    // Gunakan CameraX untuk menampilkan pratinjau kamera
//    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }
//    val cameraExecutor = remember { Executors.newSingleThreadExecutor() }
//
//    // State untuk menyimpan PreviewView
//    var previewView by remember { mutableStateOf<PreviewView?>(null) }
//
//    // Inisialisasi CameraX
//    LaunchedEffect(previewView) {
//        previewView?.let { previewView ->
//            val cameraProvider = cameraProviderFuture.get()
//            val preview = androidx.camera.core.Preview.Builder().build().also {
//                it.setSurfaceProvider(previewView.surfaceProvider)
//            }
//
//            val imageAnalyzer = ImageAnalysis.Builder()
//                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
//                .build()
//                .also {
//                    it.setAnalyzer(cameraExecutor, { image ->
//                        val bitmap = image.toBitmap()
//                        mainViewModel.processFrame(bitmap)
//                        image.close()
//                    })
//                }
//
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    lifecycleOwner, cameraSelector, preview, imageAnalyzer
//                )
//                Log.d("CameraX", "CameraX berhasil diinisialisasi")
//            } catch (exc: Exception) {
//                Log.e("CameraX", "Gagal mengikat use case", exc)
//            }
//        }
//    }
//
//    // Hentikan CameraX saat Composable dihancurkan
//    DisposableEffect(Unit) {
//        onDispose {
//            cameraProviderFuture.get().unbindAll()
//            Log.d("CameraX", "CameraX dihentikan")
//        }
//    }
//
//    // Tampilkan UI
//    Box(modifier = Modifier.fillMaxSize()) {
//        CameraPreview(
//            onPreviewReady = { previewView = it },
//            modifier = Modifier.fillMaxSize()
//        )
//
//        // Tampilkan hasil deteksi dan prediksi
//        PoseDetectionScreen(mainViewModel)
//    }
//}

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