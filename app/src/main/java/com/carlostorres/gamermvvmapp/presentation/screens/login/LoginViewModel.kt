package com.carlostorres.gamermvvmapp.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    val email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMessage: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMessage: MutableState<String> = mutableStateOf("")

    //Boton
    var isEnableLoginButton: Boolean = false
    fun enableLoginButton(){
        isEnableLoginButton = isEmailValid.value && isPasswordValid.value
    }

    fun validateEmail(){
        //Valida el correo
        if (Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrorMessage.value = ""
        }else{
            isEmailValid.value = false
            emailErrorMessage.value = "El email no es válido"
        }
        enableLoginButton()
    }

    fun validatePassword(){
        if (password.value.length >= 6){
            isPasswordValid.value = true
            passwordErrorMessage.value= ""
        }else{
            isPasswordValid.value = false
            passwordErrorMessage.value= "Se necesitan al menos 6 caracteres"
        }
        enableLoginButton()
    }


}