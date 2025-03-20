package com.manifestasi.mysporty.ui.screen.main.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.manifestasi.mysporty.ui.theme.MySportyTheme

@Composable
fun ProfileScreen(){
    Surface(modifier = Modifier.fillMaxSize().background(Color.White)) {

    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview(){
    MySportyTheme {
        ProfileScreen()
    }
}