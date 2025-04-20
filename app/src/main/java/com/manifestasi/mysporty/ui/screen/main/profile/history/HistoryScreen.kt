package com.manifestasi.mysporty.ui.screen.main.profile.history

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.model.HistoryExcersise
import com.manifestasi.mysporty.ui.theme.GrayColor1
import com.manifestasi.mysporty.ui.theme.GrayColor2
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun HistoryScreen(){
    val context = LocalContext.current
    val dummyData = listOf(
        HistoryExcersise(
            id_img = "jumping_jack",
            name = "Jumping Jack",
            repetition = 20,
            history_at = "2 minutes ago"
        ),
        HistoryExcersise(
            id_img = "pushup",
            name = "Pushup",
            repetition = 10,
            history_at = "2 minutes ago"
        ),
    )
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
                onClick = {}
            ) {
                Image(
                    painter = painterResource(R.drawable.back_navs),
                    contentDescription = "back_nav"
                )
            }
            Text(
                text = "Activity History",
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
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            itemsIndexed(dummyData, key = { index, _ ->
                index
            }){ index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    val resourceId = context.resources.getIdentifier(
                        item.id_img,
                        "drawable",
                        context.packageName
                    )
                    Image(
                        painter = painterResource(resourceId),
                        contentDescription = item.name,
                        contentScale = ContentScale.Fit, // Menggunakan Crop untuk memotong gambar
                        modifier = Modifier.size(60.dp).clip(RoundedCornerShape(12.dp))
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = item.name,
                                style = TextStyle(
                                    fontFamily = poppins,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                )
                            )
                            Spacer(Modifier.height(10.dp))
                            Text(
                                text = item.history_at,
                                style = TextStyle(
                                    fontFamily = poppins,
                                    fontSize = 10.sp,
                                    color = GrayColor1
                                )
                            )
                        }
                        Text(
                            text = "${item.repetition}x",
                            style = TextStyle(
                                fontFamily = poppins,
                                fontSize = 10.sp,
                                color = GrayColor1
                            )
                        )
                    }
                }
                if (dummyData.size-1 != index){
                    Spacer(Modifier.height(15.dp))
                    Divider(
                        modifier = Modifier.fillMaxWidth(),
                        thickness = 1.dp,
                        color = GrayColor2
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HistoryScreenPreview(){
    MySportyTheme {
        HistoryScreen()
    }
}