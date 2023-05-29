package com.carlostorres.gamermvvmapp.screens.login.components

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.screens.login.LoginScreen
import com.carlostorres.gamermvvmapp.ui.theme.DarkGray500
import com.carlostorres.gamermvvmapp.ui.theme.DarkGray700
import com.carlostorres.gamermvvmapp.ui.theme.GamerMVVMAppTheme
import com.carlostorres.gamermvvmapp.ui.theme.Pink500

@Composable
fun LoginContent() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        BoxHeader()

        CardForm()

    }
}

@Composable
fun BoxHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(270.dp)
        .background(Pink500),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.height(130.dp).padding(top = 30.dp),
                painter = painterResource(id = R.drawable.control),
                contentDescription = "Control de Xbox 360"
            )

            Text(text = "Firebase MVVM")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardForm() {
    Card(colors = CardDefaults.cardColors(containerColor = DarkGray500),
        modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp)){

        Column(modifier = Modifier.padding(horizontal = 20.dp)) {

            Text(
                text = "LOGIN",
                modifier = Modifier.padding(top = 40.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Por favor inicia sesión para continuar",
                fontSize = 12.sp,
                color = Color.Gray
            )

            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text(text = "Correo Electrónico")
            }, leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "", tint = Color.White)
            }, modifier = Modifier.padding(top = 20.dp)
            )

            OutlinedTextField(value = "", onValueChange = {}, label = {
                Text(text = "Contraseña")
            }, leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "", tint = Color.White)
            }, modifier = Modifier.padding(top = 20.dp)
            )

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 45.dp),
                onClick = {

                }) {
                Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "")
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "INICIAR SESIÓN")
            }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GamerMVVMAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LoginContent()
        }
    }
}