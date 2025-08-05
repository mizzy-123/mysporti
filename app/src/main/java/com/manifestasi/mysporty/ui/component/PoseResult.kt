package com.manifestasi.mysporty.ui.component

import android.content.Context
import android.speech.tts.TextToSpeech
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manifestasi.mysporty.Pose
import com.manifestasi.mysporty.ui.component.dialog.CompleteDialog
import com.manifestasi.mysporty.ui.component.dialog.LoadingDialog
import com.manifestasi.mysporty.ui.screen.main.home.detail.pose.PoseViewModel
import com.manifestasi.mysporty.ui.theme.BlueColor1
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.util.label
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun PoseResult(
    context: Context,
    dataPose: Pose,
    result: Int,
    poseViewModel: PoseViewModel = hiltViewModel(),
    onNavigateToMain: () -> Unit
){

    var ttsInitializeStatus by remember { mutableStateOf(false) }

    // Init TTS
    val tts = remember {
        TextToSpeech(context, TextToSpeech.OnInitListener { status ->
            if (status == TextToSpeech.SUCCESS) {
                Toast.makeText(context, "TTS Initialize success", Toast.LENGTH_SHORT).show()
                ttsInitializeStatus = true
            }
        })
    }

    LaunchedEffect(tts) {
        tts.language = Locale("id", "ID")
    }

    var repetitionCount by rememberSaveable { mutableStateOf(0) }
    val prediction: String = if (result != -1) label[result] else result.toString()
    var timeLeft by remember { mutableStateOf(3) }

    var isStart by rememberSaveable { mutableStateOf(false) }

    var currentState by rememberSaveable { mutableStateOf("") }

    var messageInstruction by rememberSaveable { mutableStateOf("Ambil posisi") }

    // Track pesan yang terakhir dibaca agar tidak diulang
    var lastSpokenMessage by rememberSaveable { mutableStateOf("") }

    var isComplete by rememberSaveable { mutableStateOf(false) }

    val isLoading = poseViewModel.isLoading.collectAsState(false)

    val successSave = poseViewModel.successSave.collectAsState(false)

    val errorSave = poseViewModel.errorSave.collectAsState(false)

    if (isLoading.value){
        LoadingDialog()
    }

    LaunchedEffect(Unit){
        poseViewModel.toastMessage.collect { event ->
            Toast.makeText(context, event, Toast.LENGTH_LONG).show()
        }
    }

    // Trigger TTS hanya jika messageInstruction berubah dan beda dari sebelumnya
    LaunchedEffect(ttsInitializeStatus, messageInstruction) {
        if ((messageInstruction.isNotEmpty() && messageInstruction != lastSpokenMessage) && ttsInitializeStatus) {
            lastSpokenMessage = messageInstruction
            delay(300) // debounce singkat
            tts.speak(messageInstruction, TextToSpeech.QUEUE_FLUSH, null, null)
        } else if (repetitionCount == dataPose.repetition){
            tts.speak(messageInstruction, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    LaunchedEffect(repetitionCount) {
        if (ttsInitializeStatus){
            tts.speak(repetitionCount.toString(), TextToSpeech.QUEUE_FLUSH, null, null)
        }

        if ((repetitionCount == dataPose.repetition+1) && !isComplete){
            isComplete = true
            poseViewModel.saveExcersise(
                image_name = dataPose.id,
                name = dataPose.name,
                repetisi = dataPose.repetition + 1
            )
        }
    }

    LaunchedEffect(successSave.value) {
        if (successSave.value){
            tts.speak("Selamat", TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    LaunchedEffect(prediction, timeLeft) {
        if (prediction == dataPose.start && timeLeft > 0){
            messageInstruction = "Bagus pertahankan"
            delay(1000L)
            timeLeft--
        } else {
            messageInstruction = "Ambil posisi"
        }

        if (timeLeft == 0) {
            currentState = dataPose.start_state
            isStart = true
        }
    }

    LaunchedEffect(prediction) {
        if (isStart && !isComplete) {
            when(dataPose.id) {
                "pushup" -> {
                    if (prediction == "pushup_down" && currentState == "up") {
                        delay(500) // Tambahkan delay untuk memastikan deteksi stabil
                        currentState = "down"
                        repetitionCount++
                    } else if (prediction == "pushup_up" && currentState == "down") {
                        delay(500) // Tambahkan delay agar transisi tidak terlalu cepat
                        currentState = "up"
                    }
                }
                "squat" -> {
                    if (prediction == "squat_down" && currentState == "up") {
                        delay(500) // Tambahkan delay untuk memastikan deteksi stabil
                        currentState = "down"
                        repetitionCount++
                    } else if (prediction == "squat_up" && currentState == "down") {
                        delay(500) // Tambahkan delay agar transisi tidak terlalu cepat
                        currentState = "up"
                    }
                }
                "jumping_jack" -> {
                    if (prediction == "jumping_jack_end" && currentState == "start") {
                        delay(500) // Tambahkan delay untuk memastikan deteksi stabil
                        currentState = "end"
                        repetitionCount++
                    } else if (prediction == "jumping_jack_start" && currentState == "down") {
                        delay(500) // Tambahkan delay agar transisi tidak terlalu cepat
                        currentState = "start"
                    }
                }
                "ab_crunch" -> {
                    if (prediction == "ab_crunch_up" && currentState == "down") {
                        delay(500) // Tambahkan delay untuk memastikan deteksi stabil
                        currentState = "up"
                        repetitionCount++
                    } else if (prediction == "ab_crunch_down" && currentState == "up") {
                        delay(500) // Tambahkan delay agar transisi tidak terlalu cepat
                        currentState = "down"
                    }
                }
                else -> {}
            }

        }
    }

    if (successSave.value || errorSave.value){
        CompleteDialog(
            title = "Selamat",
            description = "Selamat Anda berhasil menyelesaikan latihan ini",
            buttonOnclick = {
                poseViewModel.successSaveOnChange(false)
                poseViewModel.errorSaveOnChange(false)
                onNavigateToMain()
            },
            onDismiss = {}
        )
    }

    Box(modifier = Modifier.fillMaxSize()){
        Text(
            "Hasil: $prediction",
            modifier = Modifier.align(Alignment.TopStart).offset(y = 20.dp, x = 10.dp),
            style = TextStyle(
                fontSize = 20.sp,
                color = BlueColor1
            )
        )

        Text(
            "Repetition count: " + repetitionCount.toString(),
            modifier = Modifier.align(Alignment.TopEnd).offset(y = 20.dp, x = -(10.dp)),
            style = TextStyle(
                fontSize = 20.sp,
                color = BlueColor1
            )
        )

        if (isStart){
            Text(
                repetitionCount.toString(),
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 40.sp,
                    color = BlueColor1
                )
            )
        } else {
            Text(
                messageInstruction,
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    fontSize = 40.sp,
                    color = BlueColor1
                )
            )
        }

    }

    DisposableEffect(Unit) {
        onDispose {
            tts.stop()
            tts.shutdown()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PoseResultPreview(){
    MySportyTheme {
        PoseResult(
            context = LocalContext.current,
            dataPose = Pose(
                id = "",
                repetition = 9,
                link = "",
                start = "",
                start_position_image = 0,
                start_state = "",
                name = ""
            ),
            result = 0,
            onNavigateToMain = {}
        )
    }
}