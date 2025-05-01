package com.manifestasi.mysporty.ui.component.field

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.Purple1
import com.manifestasi.mysporty.ui.theme.Purple2
import com.manifestasi.mysporty.ui.theme.TextColor
import com.manifestasi.mysporty.ui.theme.TextFieldColor
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun InputWeightField(
    text: String,
    height: Dp = 56.dp,
    placeholder: String,
    onChange: (String) -> Unit
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        TextField(
            modifier = Modifier
                .weight(1f)
                .height(height),
            value = text,
            onValueChange = { newText ->
                if (newText.all { it.isDigit() }) {
                    onChange(newText)
                }
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
                    Icon(painter = painterResource(R.drawable.ic_weight), contentDescription = "profile")
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
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Box(
            modifier = Modifier.height(height)
                .width(height)
                .clip(RoundedCornerShape(14.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Purple1,
                            Purple2
                        ),
                        start = Offset(x = 48f, y = 48f),
                        end = Offset(x = -15.9741f, y = 43.0496f)
                    )
                )
        ) {
            Text(
                text = "KG",
                modifier = Modifier.align(Alignment.Center),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InputWeightFieldPreview(){
    MySportyTheme {
        InputWeightField(
            text = "",
            placeholder = "Your Weight",
            onChange = {}
        )
    }
}