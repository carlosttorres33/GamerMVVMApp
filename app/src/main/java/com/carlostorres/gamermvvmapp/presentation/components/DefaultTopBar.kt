package com.carlostorres.gamermvvmapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopBar(
    tittle: String,
    upAvailable: Boolean=false,
    navController: NavHostController? = null
) {
    TopAppBar(
        title = {
            Text(
                text = tittle, fontSize = 19.sp
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(Pink500),
        navigationIcon = {
        if (upAvailable){
            IconButton(onClick = { navController?.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White)
                
            }
        }
    })
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    DefaultTopBar(
        tittle = "Nuevo Usuario",
        upAvailable = true,
        navController = rememberNavController()
    )
}