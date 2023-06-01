package com.carlostorres.gamermvvmapp.presentation.components

import android.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500

@Composable
fun DefaultButton(
    modifier: Modifier,
    errorMessage:String = "",
    textButton:String,
    onClick :() -> Unit,
    color: ButtonColors = ButtonDefaults.buttonColors(Pink500),
    icono: ImageVector = Icons.Default.ArrowForward,
    enable: Boolean = true,
    colorText: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White,
    colorIcon: androidx.compose.ui.graphics.Color = androidx.compose.ui.graphics.Color.White
){

    Column() {
        Button(modifier = modifier,
            colors = color,
            onClick = onClick,
            enabled = enable
        ) {
            Icon(imageVector = icono, contentDescription = "", tint = colorIcon)
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = textButton, color = colorText)
        }

        //Text(text = errorMessage, modifier = Modifier.padding(top = 5.dp), fontSize = 11.sp, color = Pink700)

    }


}