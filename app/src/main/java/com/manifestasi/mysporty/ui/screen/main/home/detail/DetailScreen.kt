package com.manifestasi.mysporty.ui.screen.main.home.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.manifestasi.mysporty.ui.theme.MySportyTheme

@Composable
fun DetailScreen(){
    Surface(modifier = Modifier.fillMaxSize()) {

    }
}

@Composable
@Preview(showBackground = true)
fun DetailScreenPreview(){
    MySportyTheme {
        DetailScreen()
    }
}