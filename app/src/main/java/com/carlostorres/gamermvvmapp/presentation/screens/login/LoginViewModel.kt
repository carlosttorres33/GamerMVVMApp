package com.carlostorres.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.AuthUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {

    //State Form
    var state by mutableStateOf(LoginState())
        private set

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }
    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrorMessage : String by mutableStateOf("")
        private set

    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrorMessage by mutableStateOf("")
        private set

    //Boton
    var isEnableLoginButton: Boolean = false
    fun enableLoginButton(){
        isEnableLoginButton = isEmailValid && isPasswordValid
    }

    //LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    fun login()= viewModelScope.launch {
        loginResponse = Response.Loading
        val result = authUseCase.login(state.email, state.password)
        loginResponse = result
    }

    val currentUser = authUseCase.getCurrentUser()
    init {
        if (currentUser != null){
            loginResponse = Response.Succes(currentUser)
        }
    }

    fun validateEmail(){
        //Valida el correo
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrorMessage = ""
        }else{
            isEmailValid = false
            emailErrorMessage = "El email no es válido"
        }
        enableLoginButton()
    }

    fun validatePassword(){
        if (state.password.length >= 6){
            isPasswordValid = true
            passwordErrorMessage= ""
        }else{
            isPasswordValid = false
            passwordErrorMessage= "Se necesitan al menos 6 caracteres"
        }
        enableLoginButton()
    }


}