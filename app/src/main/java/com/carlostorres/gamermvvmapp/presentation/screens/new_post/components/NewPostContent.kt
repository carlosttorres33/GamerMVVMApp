package com.carlostorres.gamermvvmapp.presentation.screens.new_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTextField
import com.carlostorres.gamermvvmapp.presentation.components.DialogCapturePicture
import com.carlostorres.gamermvvmapp.presentation.screens.new_post.NewPostViewModel
import com.carlostorres.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500

@Composable
fun NewPostContent(viewModel: NewPostViewModel = hiltViewModel() ) {

    val state = viewModel.state

    viewModel.resultingActivityHandler.handle()

    var dialogState = remember{ mutableStateOf(false) }
    DialogCapturePicture(
        status = dialogState,
        takePhoto = { viewModel.takePhoto() },
        pickImage = {viewModel.pickImage()}
    )

    Box (
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 55.dp)
    ){

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Pink500)
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(60.dp))
                    ////////////IMAGE
                    if (viewModel.state.image != ""){
                        AsyncImage(
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(190.dp)
                                .clickable {
                                    dialogState.value = true
                                },
                            model = viewModel.state.image,
                            contentDescription = "Selected Image"
                        )
                    }else{
                        Image(
                            modifier = Modifier.height(150.dp).clickable { dialogState.value = true },
                            painter = painterResource(id = R.drawable.add_image),
                            contentDescription = ""

                        )
                        Text(
                            text = "SELECCIONA UNA IMAGEN",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 19.sp
                        )
                    }
                    //////////7

                }
            }

            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp),
                value = state.name,
                onValueChange = {
                    viewModel.onNameInput(it)
                },
                label = "Nombre del juego",
                icon = Icons.Default.Favorite,
                errorMessage = "",
                validateField = {

                }
            )

            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                value = state.description,
                onValueChange = {
                    viewModel.onDescriptionInput(it)
                },
                label = "Descripción",
                icon = Icons.Default.List,
                errorMessage = "",
                validateField = {

                }
            )

            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = "CATEGORIAS",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 19.sp
            )

            viewModel.radioOptions.forEach { option ->

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(
                            selected = (option.cat == state.cat),
                            onClick = {
                                viewModel.onCategoryInput(option.cat)
                            }
                        )
                ){
                    RadioButton(
                        selected = (option.cat == state.cat),
                        onClick = {
                            viewModel.onCategoryInput(option.cat)
                        }
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = option.cat,
                            modifier = Modifier.width(120.dp)
                        )
                        Image(
                            painter = painterResource(id = option.img),
                            contentDescription = "",
                            modifier = Modifier.height(40.dp)
                        )
                    }
                }

            }
            
        }

    }

}

@Preview (showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    GamerMVVMAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NewPostContent()
        }
    }
}