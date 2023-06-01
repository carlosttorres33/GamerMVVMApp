package com.carlostorres.gamermvvmapp.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.navigation.AppScreen
import com.carlostorres.gamermvvmapp.presentation.screens.profile.ProfileViewModel
import com.carlostorres.gamermvvmapp.presentation.ui.theme.*

@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box() {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.background),
                contentDescription = "Imagen de portada",
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "Bienvenido",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(50.dp))
                Image(
                    modifier = Modifier.size(115.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription ="Imagen de usuario"
                )
            }
            
        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Nombre del Usuario",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = "Email del Usuario",
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.height(20.dp))

        DefaultButton(
            modifier = Modifier.width(250.dp),
            textButton = "Editar Datos",
            color = ButtonDefaults.buttonColors(Color.White),
            colorText = Color.Black,
            icono = Icons.Default.Edit,
            onClick = {

            },
            colorIcon = Color.Black
        )

        Spacer(modifier = Modifier.height(10.dp))

        DefaultButton(
            modifier = Modifier.width(250.dp),
            textButton = "Cerrar Sesión",
            icono = Icons.Default.ExitToApp,
            color = ButtonDefaults.buttonColors(Pink700),
            colorText = Color.White,
            onClick = {
                viewModel.logout()
                navController.navigate(route = AppScreen.Login.route) {
                    popUpTo(AppScreen.Profile.route) {
                        inclusive = true
                    }
                }
            },
            colorIcon = Color.White
        )

    }

}
