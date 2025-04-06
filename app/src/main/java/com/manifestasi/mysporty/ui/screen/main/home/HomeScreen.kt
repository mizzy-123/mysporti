package com.manifestasi.mysporty.ui.screen.main.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manifestasi.mysporty.Detail
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.model.DataExercise
import com.manifestasi.mysporty.ui.component.card.CardBMI
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins
import com.manifestasi.mysporty.util.Excersise

@Composable
fun HomeScreen(
    rootNavController: NavHostController = rememberNavController()
){
    Surface(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(
                    start = 30.dp,
                    end = 30.dp,
                    top = 40.dp
                )
        ) {
            Text(
                text = "Welcome Back",
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 12.sp,

                )
            )

            Spacer(Modifier.height(5.dp))

            Text(
                text = "Stefani Wong",
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(30.dp))
            
            CardBMI()

            Spacer(Modifier.height(30.dp))

            Text(
                text = "Exercise",
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            )

            Spacer(Modifier.height(10.dp))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                itemsIndexed(Excersise.getData, key = { index, _ ->
                    index
                }){ index, item ->
                    Row(
                        modifier = Modifier.fillMaxWidth().clickable {
                            rootNavController.navigate(Detail(
                                id = item.id,
                                name = item.name,
                                start = item.start,
                                start_state = item.start_state,
                                link = item.link_youtube,
                                repetition = item.default_repetisi
                            ))
                        },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Image(
                            painter = painterResource(item.image),
                            contentDescription = item.name,
                            contentScale = ContentScale.Fit, // Menggunakan Crop untuk memotong gambar
                            modifier = Modifier.size(60.dp).clip(RoundedCornerShape(12.dp))
                        )

                        Row (
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
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                                Spacer(Modifier.height(5.dp))
                                Text(
                                    text = "${item.default_repetisi}x",
                                    style = TextStyle(
                                        fontFamily = poppins,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Normal
                                    )
                                )
                            }

                            Icon(
                                painter = painterResource(R.drawable.icon_next),
                                contentDescription = "icon_next"
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview(){
    MySportyTheme {
        HomeScreen()
    }
}

//Row(
//modifier = Modifier.fillMaxWidth(),
//verticalAlignment = Alignment.CenterVertically
//) {
//    Image(
//        painter = painterResource(R.drawable.jumping_jack),
//        contentDescription = "jumping jack",
//        contentScale = ContentScale.Fit, // Menggunakan Crop untuk memotong gambar
//        modifier = Modifier.size(60.dp).clip(RoundedCornerShape(12.dp))
//    )
//
//    Row (
//        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = ""
//        )
//    }
//}