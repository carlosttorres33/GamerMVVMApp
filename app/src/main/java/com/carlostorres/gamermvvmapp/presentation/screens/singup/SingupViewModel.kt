package com.carlostorres.gamermvvmapp.presentation.screens.singup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingupViewModel @Inject constructor(): ViewModel() {

    var username: MutableState<String> = mutableStateOf("")
    var isUsernameValid: MutableState<Boolean> = mutableStateOf(false)
    var usernameErrMsg: MutableState<String> = mutableStateOf("")

    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    val email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrorMessage: MutableState<String> = mutableStateOf("")

    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrorMessage: MutableState<String> = mutableStateOf("")

    //Boton
    var isEnableButton: Boolean = false
    fun enableButton(){
        isEnableButton = isEmailValid.value && isPasswordValid.value && isUsernameValid.value && isConfirmPasswordValid.value
    }

    fun validateConfirmPassword(){
        if (password.value == confirmPassword.value){
            isConfirmPasswordValid.value = true
            confirmPasswordErrMsg.value = ""
        }else{
            isConfirmPasswordValid.value = false
            confirmPasswordErrMsg.value = "Las contraseñas no coiinciden"
        }

        enableButton()
    }

    fun validateUsername(){
        if (username.value.length >= 5){
            isUsernameValid.value = true
            usernameErrMsg.value = ""
        }else{
            isUsernameValid.value = false
            usernameErrMsg.value = "Al menos 5 caracteres"
        }
        enableButton()
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
        enableButton()
    }

    fun validatePassword(){
        if (password.value.length >= 6){
            isPasswordValid.value = true
            passwordErrorMessage.value= ""
        }else{
            isPasswordValid.value = false
            passwordErrorMessage.value= "Se necesitan al menos 6 caracteres"
        }
        enableButton()
    }


}