package com.carlostorres.gamermvvmapp.presentation.screens.profile_edit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.use_cases.users.UsersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases
) : ViewModel() {

    var state by mutableStateOf(ProfileEditState())
        private set

    var isEnableButton: Boolean = false

    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    init {
        state = state.copy(username = user.username)
    }

    var isUsernameValid by mutableStateOf(false)
        private set
    var usernameErrMsg by mutableStateOf("")
        private set

    fun onUsernameInput(username: String) {
        state = state.copy(username = username)

    }

    fun validateUsername() {
        if (state.username.length >= 5) {
            isUsernameValid = true
            usernameErrMsg = ""
        } else {
            isUsernameValid = false
            usernameErrMsg = "Al menos 5 caracteres"
        }
        enableButton()
    }

    fun enableButton() {
        isEnableButton =
            isUsernameValid
    }

    fun onUpdate(){
        val myUser = User(
            id = user.id,
            username = state.username,
            image = ""

        )
        update(myUser)
    }

    fun update (user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result
    }

}