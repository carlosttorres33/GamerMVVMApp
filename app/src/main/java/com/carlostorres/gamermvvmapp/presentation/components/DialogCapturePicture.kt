package com.carlostorres.gamermvvmapp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogCapturePicture(
    status: MutableState<Boolean>,
    takePhoto: () -> Unit,
    pickImage: () -> Unit
) {

    if (status.value){
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            onDismissRequest = { status.value = false },
            containerColor = Color.White,
            confirmButton = {
                Button(
                    onClick = {
                        status.value = false
                        pickImage()
                    } ,
                    modifier = Modifier.width(130.dp)
                ) {
                    Text(text = "Galeria")
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        status.value = false
                        takePhoto()
                    } ,
                    modifier = Modifier.width(130.dp))
                {
                    Text(text = "Camara")
                }
            },
            title = {
                Text(text = "Selecciona una opción", fontSize = 20.sp, color = Color.Black)
            },

        )
    }

}