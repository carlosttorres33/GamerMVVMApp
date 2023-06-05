package com.carlostorres.gamermvvmapp.presentation.screens.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ProgressBar() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()){
        CircularProgressIndicator()
    }
}