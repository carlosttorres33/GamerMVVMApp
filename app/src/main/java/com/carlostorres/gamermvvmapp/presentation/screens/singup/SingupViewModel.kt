package com.carlostorres.gamermvvmapp.presentation.screens.singup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.AuthUseCase
import com.carlostorres.gamermvvmapp.domain.model.*
import com.carlostorres.gamermvvmapp.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingupViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    var state by mutableStateOf(SingUpState())
        private set

    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    var isConfirmPasswordValid by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
        private set

    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrorMessage by mutableStateOf("")
        private set

    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrorMessage by mutableStateOf("")
        private set


    var user = User()

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }
    fun onUsernameInput(username: String){
        state = state.copy(username = username)

    }
    fun onPasswordInput(password: String){
        state = state.copy(passwoord = password)
    }
    fun onConfirmPasswordInput(passwordConfirm: String){
        state = state.copy(confirmPassword = passwordConfirm)
    }

    //Boton
    var isEnableButton: Boolean = false
    fun enableButton() {
        isEnableButton =
            isEmailValid && isPasswordValid && isUsernameValid && isConfirmPasswordValid
    }

    fun validateConfirmPassword() {
        if (state.passwoord == state.confirmPassword) {
            isConfirmPasswordValid = true
            confirmPasswordErrMsg = ""
        } else {
            isConfirmPasswordValid = false
            confirmPasswordErrMsg = "Las contraseñas no coiinciden"
        }

        enableButton()
    }

    fun validateUsername() {
        if (state.username.length >= 5) {
            isUsernameValid = true
            usernameErrMsg= ""
        } else {
            isUsernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }
        enableButton()
    }


    fun onSingUp() {
        user.username = state.username
        user.email = state.email
        user.password = state.passwoord


        singUp(user)

    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCase.getCurrentUser()!!.uid
        usersUseCases.create(user)
    }

    var singupResponse by mutableStateOf<Response<FirebaseUser>?>(null)
        private set

    fun singUp(user: User) = viewModelScope.launch {
        singupResponse = Response.Loading
        val result = authUseCase.singUp(user)
        singupResponse = result
    }

    fun validateEmail() {
        //Valida el correo
        if (Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            isEmailValid = true
            emailErrorMessage = ""
        } else {
            isEmailValid = false
            emailErrorMessage = "El email no es válido"
        }
        enableButton()
    }

    fun validatePassword() {
        if (state.passwoord.length >= 6) {
            isPasswordValid = true
            passwordErrorMessage= ""
        } else {
            isPasswordValid = false
            passwordErrorMessage = "Se necesitan al menos 6 caracteres"
        }
        enableButton()
    }


}