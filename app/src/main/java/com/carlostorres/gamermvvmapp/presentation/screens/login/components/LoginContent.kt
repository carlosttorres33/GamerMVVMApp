package com.carlostorres.gamermvvmapp.presentation.screens.login.components


import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.presentation.components.DefaultButton
import com.carlostorres.gamermvvmapp.presentation.components.DefaultTextField
import com.carlostorres.gamermvvmapp.presentation.navigation.AppScreen
import com.carlostorres.gamermvvmapp.presentation.screens.login.LoginViewModel
import com.carlostorres.gamermvvmapp.presentation.ui.theme.DarkGray500
import com.carlostorres.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500

@Composable
fun LoginContent(navController: NavController, viewModel: LoginViewModel = hiltViewModel()) {

    //var context = LocalContext.current
    val loginFlow = viewModel.loginFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(270.dp)
                .background(Pink500),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .height(130.dp)
                        .padding(top = 30.dp),
                    painter = painterResource(id = R.drawable.control),
                    contentDescription = "Control de Xbox 360"
                )

                Text(text = "Firebase MVVM")
            }

        }

        Card(
            colors = CardDefaults.cardColors(containerColor = DarkGray500),
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 200.dp)
        ) {

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

                DefaultTextField(
                    modifier = Modifier.padding(top = 25.dp),
                    value = viewModel.email.value,
                    onValueChange = { viewModel.email.value = it },
                    label = "E-Mail",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMessage = viewModel.emailErrorMessage.value,
                    validateField = {
                        viewModel.validateEmail()
                    }
                )

                DefaultTextField(
                    modifier = Modifier.padding(top = 5.dp),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    keyboardType = KeyboardType.Password,
                    errorMessage = viewModel.passwordErrorMessage.value,
                    validateField = {
                        viewModel.validatePassword()
                    }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 45.dp),
                    textButton = "Iniciar Sesión",
                    onClick = {

                        viewModel.login()

                    },
                    enable = viewModel.isEnableLoginButton,
                    colorIcon = Color.White
                )

            }

        }

    }

    loginFlow.value.let{
        when(it){
            Response.Loading ->{
                Box(contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()){
                    CircularProgressIndicator()
                }
            }
            is Response.Succes -> {
                LaunchedEffect(Unit){
                    navController.navigate(route = AppScreen.Profile.route){
                        popUpTo(AppScreen.Login.route) {inclusive = true}
                    }
                }
                Toast.makeText(LocalContext.current, "Bienvenido :D", Toast.LENGTH_SHORT).show()
            }
            is Response.Faliure -> {
                Toast.makeText(LocalContext.current, it.exception.message ?: "Error Desconocido" , Toast.LENGTH_SHORT).show()
            }
            else -> {
                //Toast.makeText(LocalContext.current, "Error Desconocido" , Toast.LENGTH_SHORT).show()
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
            LoginContent(rememberNavController())
        }
    }
}