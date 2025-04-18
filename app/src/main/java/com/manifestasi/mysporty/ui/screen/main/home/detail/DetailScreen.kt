package com.manifestasi.mysporty.ui.screen.main.home.detail

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.Detail
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.component.button.ButtonStart
import com.manifestasi.mysporty.ui.component.webview.EmbedYoutubeView
import com.manifestasi.mysporty.ui.theme.GrayColor1
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.Purple1
import com.manifestasi.mysporty.ui.theme.Purple2
import com.manifestasi.mysporty.ui.theme.poppins
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.rememberSnapperFlingBehavior
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun DetailScreen(
    dataDetail: Detail,
    onSelectedRepetition: (Int) -> Unit,
    onNavigateToPose: () -> Unit
){
    val dataRepetition = dataDetail.repetition
    val list = (1..dataRepetition).toList() // Data yang akan ditampilkan (misal angka repetisi)
    var selectedIndex by remember { mutableIntStateOf(dataRepetition) }
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedIndex)
    val coroutineScope = rememberCoroutineScope()

    // Scroll otomatis ke item tengah setelah berhenti scrolling
    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
//            val selectedItem = listState.firstVisibleItemIndex + 1

            val centerItemIndex = listState.layoutInfo.visibleItemsInfo
                .minByOrNull { abs(it.offset) }?.index ?: selectedIndex

            selectedIndex = centerItemIndex
            onSelectedRepetition(selectedIndex)
            coroutineScope.launch {
                listState.animateScrollToItem(selectedIndex)
            }
//            selectedIndex = selectedItem
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .verticalScroll(rememberScrollState())
        .padding(
            start = 30.dp,
            end = 30.dp,
            top = 40.dp
        )
    ) {
        IconButton(
            onClick = {}
        ) {
            Image(
                painter = painterResource(R.drawable.back_navs),
                contentDescription = "back_nav"
            )
        }

        Spacer(Modifier.height(30.dp))

//        Box(
//            modifier = Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(22.dp))
//                .background(Color.Red)
//        )

        EmbedYoutubeView(dataDetail.link)

        Spacer(Modifier.height(20.dp))

        Text(
            text = dataDetail.name,
            style = TextStyle(
                fontFamily = poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(Modifier.height(5.dp))

        Text(
            text = "390 Calories Burn",
            style = TextStyle(
                fontFamily = poppins,
                fontSize = 12.sp,
                color = GrayColor1
            )
        )

        Spacer(Modifier.height(30.dp))

        Text(
            text = "Descriptions",
            style = TextStyle(
                fontFamily = poppins,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        )

        Spacer(Modifier.height(15.dp))

        Text(
            text = dataDetail.description,
            style = TextStyle(
                fontFamily = poppins,
                fontSize = 12.sp,
                color = GrayColor1
            )
        )

        Spacer(Modifier.height(30.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "How To Do It",
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Text(
                text = "${dataDetail.tutorialList.size} Steps",
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = GrayColor1
                )
            )
        }

        Spacer(Modifier.height(14.dp))

        Column {
            dataDetail.tutorialList.forEachIndexed { index, tutorial ->
                val num = (index+1).toString().padStart(2, '0')

                if (dataDetail.tutorialList.size-1 != index){
                    Row (verticalAlignment = Alignment.Top) {

                        Text(
                            text = num,
                            style = TextStyle(
                                fontFamily = poppins,
                                fontSize = 14.sp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Purple1,
                                        Purple2
                                    ),
                                    start = Offset(x = 14f, y = 17f),
                                    end = Offset(x = -4.72101f, y = 16.0342f)
                                )
                            )
                        )

                        Spacer(Modifier.width(13.dp))

                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.circle_1),
                                contentDescription = "circle"
                            )
                            Canvas(modifier = Modifier.height(100.dp).width(2.dp)) {
                                drawLine(
                                    color = Purple1,
                                    start = Offset(size.width / 2, 0f),  // Mulai dari atas
                                    end = Offset(size.width / 2, size.height), // Sampai ke bawah
                                    strokeWidth = 4f,
                                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f) // 10px garis, 10px kosong
                                )
                            }
                        }

                        Spacer(Modifier.width(15.dp))

                        Column {
                            Text(
                                text = tutorial.name,
                                style = TextStyle(
                                    fontFamily = poppins,
                                    fontSize = 14.sp
                                )
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = tutorial.name,
                                style = TextStyle(
                                    fontFamily = poppins,
                                    fontSize = 12.sp,
                                    color = GrayColor1
                                )
                            )
                        }
                    }
                } else {
                    Row (verticalAlignment = Alignment.Top) {

                        Text(
                            text = num,
                            style = TextStyle(
                                fontFamily = poppins,
                                fontSize = 14.sp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Purple1,
                                        Purple2
                                    ),
                                    start = Offset(x = 14f, y = 17f),
                                    end = Offset(x = -4.72101f, y = 16.0342f)
                                )
                            )
                        )

                        Spacer(Modifier.width(13.dp))

                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.circle_1),
                                contentDescription = "circle"
                            )
                        }

                        Spacer(Modifier.width(15.dp))

                        Column {
                            Text(
                                text = tutorial.name,
                                style = TextStyle(
                                    fontFamily = poppins,
                                    fontSize = 14.sp
                                )
                            )
                            Spacer(Modifier.height(5.dp))
                            Text(
                                text = tutorial.description,
                                style = TextStyle(
                                    fontFamily = poppins,
                                    fontSize = 12.sp,
                                    color = GrayColor1
                                )
                            )
                        }
                    }
                }

            }

            Spacer(Modifier.height(30.dp))
            Text(
                text = "Custom Repetitions",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(Modifier.height(15.dp))
            LazyColumn(
                state = listState,
                modifier = Modifier.height(120.dp), // Tinggi area scroll
                flingBehavior = rememberSnapperFlingBehavior(listState), // Auto snap ke item
                verticalArrangement = Arrangement.Center,
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(list.size) { index ->
//                    val isSelected = (index == listState.firstVisibleItemIndex + 1) // Item tengah
                    val isSelected = index == selectedIndex
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .alpha(if (isSelected) 1f else 0.3f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fire), // Gunakan icon yang sesuai
                            contentDescription = null,
                            tint = if (isSelected) Color.Red else Color.LightGray,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${list[index]} times",
                            fontSize = if (isSelected) 24.sp else 20.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color.Black else Color.Gray
                        )
                    }
                }
            }
        }

        Spacer(Modifier.height(30.dp))

        ButtonStart(
            onClick = {
                onNavigateToPose()
            }
        )

        Spacer(Modifier.height(100.dp))
    }
}


@Composable
@Preview(showBackground = true)
fun DetailScreenPreview(){
    MySportyTheme {
        DetailScreen(
            dataDetail = Detail(
                id = "",
                name = "",
                start = "",
                start_state = "",
                description = "",
                link = "",
                repetition = 4,
                tutorialList = emptyList()
            ),
            onSelectedRepetition = {},
            onNavigateToPose = {}
        )
    }
}