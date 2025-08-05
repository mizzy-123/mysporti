package com.manifestasi.mysporty.ui.component.button

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manifestasi.mysporty.ui.theme.ButtonColor1
import com.manifestasi.mysporty.ui.theme.MySportyTheme

@Composable
fun ButtonSwitchSkeletonDraw(
    modifier: Modifier = Modifier,
    isDrawLandmark: Boolean = true,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(100),
        colors = ButtonDefaults.buttonColors(
            containerColor = ButtonColor1
        )
    ) {
        Icon(
            modifier = Modifier.size(60.dp),
            imageVector = if (isDrawLandmark) Icons.Default.VisibilityOff else Icons.Default.Visibility,
            contentDescription = ""
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ButtonSwitchSkeletonDrawPreview(){
    MySportyTheme {
        ButtonSwitchSkeletonDraw(
            onClick = {}
        )
    }
}