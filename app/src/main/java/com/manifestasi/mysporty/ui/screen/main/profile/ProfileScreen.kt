package com.manifestasi.mysporty.ui.screen.main.profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.manifestasi.mysporty.History
import com.manifestasi.mysporty.PersonalData
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.component.button.ButtonEdit
import com.manifestasi.mysporty.ui.component.card.CardInformation
import com.manifestasi.mysporty.ui.component.dialog.QuestionDialog
import com.manifestasi.mysporty.ui.component.menu.MenuProfile
import com.manifestasi.mysporty.ui.theme.GrayColor1
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins
import com.manifestasi.mysporty.util.finishAffinity

@Composable
fun ProfileScreen(
    rootNavController: NavHostController = rememberNavController(),
    viewModel: ProfileViewModel = hiltViewModel()
){
    val context = LocalContext.current
    var logoutDialog by rememberSaveable { mutableStateOf(false) }

    if (logoutDialog){
        QuestionDialog(
            onDismiss = {},
            title = "",
            description = "",
            btnClickNo = {
                logoutDialog = false
            },
            btnClickYes = {
                viewModel.logout()
            }
        )
    }

    LaunchedEffect(Unit) {
        viewModel.loadingLogout.collect { event ->
            Toast.makeText(context, "Loading...", Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit) {
        viewModel.logoutSuccess.collect { event ->
            if (event){
                finishAffinity(context)
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 30.dp)
        ) {
            Spacer(Modifier.height(44.dp))
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Profile",
                    modifier = Modifier.align(Alignment.Center),
                    style = TextStyle(
                        fontFamily = poppins,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                )
            }
            Spacer(Modifier.height(42.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        "Stefani Wong",
                        style = TextStyle(
                            fontFamily = poppins,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Spacer(Modifier.height(5.dp))
                    Text(
                        "Lose a Fat Program",
                        style = TextStyle(
                            fontFamily = poppins,
                            fontSize = 12.sp,
                            color = GrayColor1
                        )
                    )
                }
//                ButtonEdit(
//                    onClick = {}
//                )
            }

            Spacer(Modifier.height(23.dp))

            CardInformation(
                height = 180,
                weight = 65,
                age = 22
            )

            Spacer(Modifier.height(30.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),

                elevation = CardDefaults.cardElevation(10.dp)
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.White)
                        .padding(
                            start = 20.dp,
                            end = 15.dp,
                            top = 20.dp,
                            bottom = 22.dp
                        ),
                ){
                    Text(
                        text = "Account",
                        style = TextStyle(
                            fontFamily = poppins,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                    )

                    Spacer(Modifier.height(15.dp))

                    MenuProfile(
                        iconImage = R.drawable.ic_profile,
                        text = "Personal Data",
                        onClick = {
                            rootNavController.navigate(PersonalData)
                        }
                    )

                    Spacer(Modifier.height(15.dp))

                    MenuProfile(
                        iconImage = R.drawable.ic_activity,
                        text = "Acitivity History",
                        onClick = {
                            rootNavController.navigate(History)
                        }
                    )

                    Spacer(Modifier.height(15.dp))

                    MenuProfile(
                        iconImage = R.drawable.ic_logout,
                        text = "Log Out",
                        onClick = {}
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfileScreenPreview(){
    MySportyTheme {
        ProfileScreen()
    }
}