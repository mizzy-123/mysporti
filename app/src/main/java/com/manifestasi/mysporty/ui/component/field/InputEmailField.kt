package com.manifestasi.mysporty.ui.component.field

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.TextColor
import com.manifestasi.mysporty.ui.theme.TextFieldColor
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun InputEmailField(
    text: String,
    placeholder: String,
    onChange: (String) -> Unit
){
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            onChange(it)
        },
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = TextFieldColor,
            focusedContainerColor = TextFieldColor,
            disabledContainerColor = TextFieldColor,
            errorContainerColor = TextFieldColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(15.dp))
                Icon(painter = painterResource(R.drawable.message), contentDescription = "message")
                Spacer(Modifier.width(10.dp))
            }
        },
        placeholder = {
            Text(
                placeholder,
                fontFamily = poppins,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = TextColor
                )
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun InputEmailFieldPreview(){
    MySportyTheme {
        InputEmailField(
            text = "",
            placeholder = "Email",
            onChange = {}
        )
    }
}