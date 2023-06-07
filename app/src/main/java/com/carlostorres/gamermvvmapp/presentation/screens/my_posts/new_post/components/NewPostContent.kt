package com.carlostorres.gamermvvmapp.presentation.screens.my_posts.new_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTextField
import com.carlostorres.gamermvvmapp.presentation.navigation.RootNavGraph
import com.carlostorres.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500

data class CategoryRadioButton(
    var cat: String,
    var img: Int
)

@Composable
fun NewPostContent() {

    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("X-Box", R.drawable.icon_xbox),
        CategoryRadioButton("Nintendo", R.drawable.icon_nintendo),
        CategoryRadioButton("Móvil", R.drawable.icon_pc),
    )

    Box (
        modifier = Modifier.fillMaxWidth().padding(bottom = 55.dp)
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
                    Image(
                        modifier = Modifier.height(150.dp),
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
            }

            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 30.dp),
                value = "",
                onValueChange = {

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
                value = "",
                onValueChange = {

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

            radioOptions.forEach { option ->

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .selectable(
                            selected = false,
                            onClick = {

                            }
                        )
                ){
                    RadioButton(
                        selected = false,
                        onClick = {}
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