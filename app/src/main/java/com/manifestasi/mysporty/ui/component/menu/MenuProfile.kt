package com.manifestasi.mysporty.ui.component.menu

import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.manifestasi.mysporty.R
import com.manifestasi.mysporty.ui.theme.GrayColor1
import com.manifestasi.mysporty.ui.theme.MySportyTheme
import com.manifestasi.mysporty.ui.theme.poppins

@Composable
fun MenuProfile(
    iconImage: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                modifier = Modifier.width(20.dp).height(20.dp),
                painter = painterResource(iconImage),
                contentDescription = text
            )

            Text(
                text = text,
                style = TextStyle(
                    fontFamily = poppins,
                    fontSize = 12.sp,
                    color = GrayColor1
                )
            )
        }

        Icon(
            modifier = Modifier.width(18.dp).height(18.dp),
            imageVector = Icons.Default.ArrowForwardIos,
            contentDescription = "arrow",
            tint = GrayColor1
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MenuProfilePreview(){
    MySportyTheme {
        MenuProfile(
            iconImage = R.drawable.ic_profile,
            text = "Personal Data",
            onClick = {}
        )
    }
}