package com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTextField
import com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.carlostorres.gamermvvmapp.presentation.ui.theme.DarkGray500
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500

@Composable
fun ProfileEditContent(navController: NavController, viewModel: ProfileEditViewModel = hiltViewModel()) {

    val state = viewModel.state

    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(290.dp)
                .background(Pink500),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(70.dp))
                Image(
                    modifier = Modifier
                        .height(120.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "user default image"
                )

            }

        }

        Card(
            colors = CardDefaults.cardColors(containerColor = DarkGray500),
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 220.dp)
        ) {

            Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                Text(
                    text = "Actualizar",
                    modifier = Modifier.padding(top = 40.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Ingresa tus nuevos datos",
                    fontSize = 12.sp,
                    color = Color.Gray
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = state.username,
                    onValueChange = { viewModel.onUsernameInput(it) },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    keyboardType = KeyboardType.Text,
                    errorMessage = viewModel.usernameErrMsg,
                    validateField = {
                        viewModel.validateUsername()
                    }
                )

                DefaultButton(
                    textButton = "Actualizar",
                    onClick = {
                        viewModel.onUpdate()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 40.dp),
                    colorIcon = Color.White,
                    icono = Icons.Default.Edit
                )

            }

        }

    }

}