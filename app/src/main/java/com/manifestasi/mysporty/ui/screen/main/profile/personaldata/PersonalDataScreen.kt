package com.manifestasi.mysporty.ui.screen.main.profile.personaldata

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.component.button.ButtonSave
import com.manifestasi.mysporty.ui.component.field.InputHeightField
import com.manifestasi.mysporty.ui.component.field.InputNameField
import com.manifestasi.mysporty.ui.component.field.InputWeightField
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun PersonalDataScreen(
    onNavigateBack: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize()
            .background(Color.White)
            .padding(
                start = 30.dp,
                top = 40.dp,
                end = 30.dp,
                bottom = 30.dp
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onNavigateBack()
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.back_navs),
                    contentDescription = "back_nav"
                )
            }
            Text(
                text = "Personal Data",
                modifier = Modifier.fillMaxWidth(),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppins,
                    textAlign = TextAlign.Center
                )
            )
        }

        Spacer(Modifier.height(30.dp))

        InputNameField(
            text = "",
            placeholder = "First Name",
            onChange = {}
        )

        Spacer(Modifier.height(15.dp))

        InputNameField(
            text = "",
            placeholder = "Last Name",
            onChange = {}
        )

        Spacer(Modifier.height(15.dp))

        InputWeightField(
            text = "",
            placeholder = "Your Weight",
            onChange = {}
        )

        Spacer(Modifier.height(15.dp))

        InputHeightField(
            text = "",
            placeholder = "Your Height",
            onChange = {}
        )

        Spacer(Modifier.height(60.dp))

        ButtonSave(
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDataScreenPreview(){
    MySportyTheme {
        PersonalDataScreen(
            onNavigateBack = {}
        )
    }
}