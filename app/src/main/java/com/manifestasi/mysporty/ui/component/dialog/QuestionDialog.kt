package com.manifestasi.mysporty.ui.component.dialog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.ButtonColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun QuestionDialog(
    onDismiss: () -> Unit,
    title: String,
    description: String,
    btnClickYes: () -> Unit,
    btnClickNo: () -> Unit
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
            ) {

                Spacer(Modifier.height(37.dp))

                Image(
                    painter = painterResource(R.drawable.ic_question),
                    contentDescription = "icon verify"
                )

                Spacer(Modifier.height(18.dp))

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

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            btnClickNo()
                        },
                        modifier = Modifier
                            .height(44.dp)
                            .width(121.dp),
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
                                text = "Tidak",
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

                    Spacer(Modifier.width(16.dp))

                    Button(
                        onClick = {
                            btnClickYes()
                        },
                        modifier = Modifier
                            .height(44.dp)
                            .width(121.dp),
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
                                text = "Ya",
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

                }

                Spacer(Modifier.height(30.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuestionDialogPreview(){
    MySportyTheme {
        QuestionDialog(
            title = "Keluar Akun",
            description = "Apakah anda yakin keluar akun?",
            onDismiss = {},
            btnClickNo = {},
            btnClickYes = {}
        )
    }
}
