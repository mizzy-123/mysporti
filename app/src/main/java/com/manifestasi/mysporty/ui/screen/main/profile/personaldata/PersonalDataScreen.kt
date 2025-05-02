package com.manifestasi.mysporty.ui.screen.main.profile.personaldata

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.data.model.Profile
import com.manifestasi.mysporty.ui.component.button.ButtonSave
import com.manifestasi.mysporty.ui.component.field.InputHeightField
import com.manifestasi.mysporty.ui.component.field.InputNameField
import com.manifestasi.mysporty.ui.component.field.InputNumberField
import com.manifestasi.mysporty.ui.component.field.InputWeightField
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins
import kotlinx.coroutines.flow.collect

@Composable
fun PersonalDataScreen(
    onNavigateBack: () -> Unit,
    onNavigateToMain: () -> Unit,
    profile: Profile? = null,
    viewModel: PersonalDataViewModel = hiltViewModel()
){
    val context = LocalContext.current

    val height = viewModel.height.collectAsState()
    val weight = viewModel.weight.collectAsState()
    val age = viewModel.age.collectAsState()
    val firstName = viewModel.firstname.collectAsState()
    val lastName = viewModel.lastname.collectAsState()

    val isLoading = viewModel.loadingUpdateProfile.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.updateProfileSuccess.collect { event ->
            if (event){
                onNavigateToMain()
            }
        }
    }

    LaunchedEffect(Unit){
        viewModel.errorMessageUpdateProfile.collect { event ->
            Toast.makeText(context, event, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit){
        viewModel.successMessageUpdateProfile.collect { event ->
            Toast.makeText(context, event, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(Unit){
        viewModel.onChangeAge(profile?.age?.toString() ?: "")
        viewModel.onChangeHeight(profile?.height?.toString() ?: "")
        viewModel.onChangeLastName(profile?.last_name ?: "")
        viewModel.onChangeFirstName(profile?.first_name ?: "")
        viewModel.onChangeWeight(profile?.weight?.toString() ?: "")
    }

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
            text = firstName.value,
            placeholder = "First Name",
            onChange = {
                viewModel.onChangeFirstName(it)
            }
        )

        Spacer(Modifier.height(15.dp))

        InputNameField(
            text = lastName.value,
            placeholder = "Last Name",
            onChange = {
                viewModel.onChangeLastName(it)
            }
        )

        Spacer(Modifier.height(15.dp))

        InputNumberField(
            text = age.value,
            placeholder = "Age",
            onChange = {
                viewModel.onChangeAge(it)
            }
        )

        Spacer(Modifier.height(15.dp))

        InputWeightField(
            text = weight.value,
            placeholder = "Your Weight",
            onChange = {
                viewModel.onChangeWeight(it)
            }
        )

        Spacer(Modifier.height(15.dp))

        InputHeightField(
            text = height.value,
            placeholder = "Your Height",
            onChange = {
                viewModel.onChangeHeight(it)
            }
        )

        Spacer(Modifier.height(60.dp))

        ButtonSave(
            onClick = {
                viewModel.updateProfile()
            },
            isDisabled = isLoading.value,
            isLoading = isLoading.value
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PersonalDataScreenPreview(){
    MySportyTheme {
        PersonalDataScreen(
            onNavigateBack = {},
            onNavigateToMain = {}
        )
    }
}