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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCase: AuthUseCase): ViewModel() {

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

    private val _loginFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val loginFlow: StateFlow<Response<FirebaseUser>?> = _loginFlow
    fun login()= viewModelScope.launch {
        _loginFlow.value = Response.Loading
        val result = authUseCase.login(email.value, password.value)
        _loginFlow.value = result
    }

    val currentUser = authUseCase.getCurrentUser()
    init {
        if (currentUser != null){
            _loginFlow.value = Response.Succes(currentUser)
        }
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