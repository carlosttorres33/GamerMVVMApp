package com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.components

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTextField
import com.carlostorres.gamermvvmapp.presentation.components.DialogCapturePicture
import com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.ProfileEditViewModel
import com.carlostorres.gamermvvmapp.presentation.ui.theme.DarkGray500
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500
import com.carlostorres.gamermvvmapp.presentation.utils.ComposeFileProvider

@Composable
fun ProfileEditContent(navController: NavController, viewModel: ProfileEditViewModel = hiltViewModel()) {

    //val context = LocalContext.current
    val state = viewModel.state
    viewModel.resultingActivityHandler.handle()

    var dialogState = remember{ mutableStateOf(false) }
    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = {viewModel.pickImage()}
    )

    /**************
    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { viewModel.onGalleryResult(it) }
        }
    )
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = {
            viewModel.onCameraResult(it)
        }
    )
     ***************/

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
                if (viewModel.state.image != ""){
                    AsyncImage(
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(120.dp)
                            .width(120.dp)
                            .clip(CircleShape)
                            .clickable {
                                       dialogState.value = true
                            },
                        model = viewModel.state.image,
                        contentDescription = "Selected Image"
                    )
                }else{
                    Image(
                        modifier = Modifier
                            .height(120.dp)
                            .clickable {
                                //viewModel.pickImage()
                                //viewModel.takePhoto()
                                       dialogState.value = true
                            },
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = "user default image"
                    )
                }

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
                        viewModel.saveImage()
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