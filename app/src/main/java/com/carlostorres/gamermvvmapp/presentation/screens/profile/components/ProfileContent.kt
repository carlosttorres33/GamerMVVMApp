package com.carlostorres.gamermvvmapp.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.MainActivity
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.navigation.AuthScreen
import com.carlostorres.gamermvvmapp.presentation.navigation.DetailsScreen
import com.carlostorres.gamermvvmapp.presentation.navigation.Graph
import com.carlostorres.gamermvvmapp.presentation.screens.profile.ProfileViewModel
import com.carlostorres.gamermvvmapp.presentation.ui.theme.*

@Composable
fun ProfileContent(
    navController: NavHostController,
    viewModel: ProfileViewModel = hiltViewModel()
) {

    val activity = LocalContext.current as? Activity

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
                if (viewModel.userData.image != ""){
                    AsyncImage(
                        model = viewModel.userData.image,
                        contentDescription = "User Profile Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(CircleShape)
                    )
                } else {
                    Image(
                        modifier = Modifier.size(115.dp),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "Imagen de usuario"
                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = viewModel.userData.username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = viewModel.userData.email,
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
                navController.navigate(DetailsScreen.ProfileEdit.passUser(viewModel.userData.toJson()))
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
                activity?.finish()
                activity?.startActivity(Intent(activity, MainActivity::class.java))
            },
            colorIcon = Color.White
        )

    }

}
