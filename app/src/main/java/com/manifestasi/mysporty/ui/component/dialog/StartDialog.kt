package com.manifestasi.mysporty.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.ButtonColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun StartDialog(
    title: String,
    description: String,
    onDismiss: () -> Unit,
    buttonOnclick: () -> Unit
){
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier
                .width(346.dp)
        ) {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                Spacer(Modifier.height(37.dp))

//                Image(
//                    painter = painterResource(R.drawable.icon_x),
//                    contentDescription = "icon x"
//                )

//                Spacer(Modifier.height(18.dp))

                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = poppins,
                        fontWeight = FontWeight(500),
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(Modifier.height(3.dp))

                Text(
                    text = description,
                    style = TextStyle(
                        fontFamily = poppins,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = {
                        buttonOnclick()
                    },
                    modifier = Modifier
                        .height(46.dp)
                        .width(260.dp),
                    shape = RoundedCornerShape(99.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent,
                    ),
                    elevation = ButtonDefaults.buttonElevation(10.dp),
                    contentPadding = PaddingValues()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        ButtonColor2,
                                        ButtonColor1
                                    )
                                )
                            )
                    ) {
                        Text(
                            text = "Start",
                            style = TextStyle(
                                fontSize = 16.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center,
                                fontFamily = poppins,
                            ),
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }

                Spacer(Modifier.height(30.dp))
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun StartDialogPreview(){
    MySportyTheme {
        StartDialog(
            title = "Sebelum dimulai",
            description = "Sebelum memulai posisikan depan",
            onDismiss = {},
            buttonOnclick = {}
        )
    }
}