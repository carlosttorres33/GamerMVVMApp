package com.carlostorres.gamermvvmapp.presentation.components

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
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink700

@Composable
fun DefaultButton(
    modifier: Modifier,
    errorMessage:String = "",
    textButton:String,
    onClick :() -> Unit,
    color: ButtonColors = ButtonDefaults.buttonColors(Pink500),
    icono: ImageVector = Icons.Default.ArrowForward,
    enable: Boolean = true
){

    Column() {
        Button(modifier = modifier,
            colors = color,
            onClick = onClick,
            enabled = enable
        ) {
            Icon(imageVector = icono, contentDescription = "")
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = textButton)
        }

        Text(text = errorMessage, modifier = Modifier.padding(top = 5.dp), fontSize = 11.sp, color = Pink700)

    }


}