package com.carlostorres.gamermvvmapp.presentation.screens.profile_edit

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.use_cases.users.UsersUseCases
import com.carlostorres.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.carlostorres.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
) : ViewModel() {

    var state by mutableStateOf(ProfileEditState())
        private set
    var isEnableButton: Boolean = false
    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)
    /////Response Upload Username
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    /////Response Save Image
    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set
    //IMAGES
    //var imagesUri by mutableStateOf("")
    //var hasImage by mutableStateOf<Boolean>(false)
    var file: File? = null

    init {
        state = state.copy(username = user.username, image = user.image)
    }

    fun saveImage() = viewModelScope.launch {

        if (file != null){
            saveImageResponse = Response.Loading
            val result = usersUseCases.saveImage(file!!)
            saveImageResponse = result
        }
    }

    /*FUNCIONES PARA ACCEDER SOLO A LA CAMARA O GALERIA
    fun onGalleryResult(uri: Uri?){
        hasImage = uri != null
        imagesUri = uri

    }
    fun onCameraResult(result: Boolean){
        hasImage = result
    }*/

    val resultingActivityHandler = ResultingActivityHandler()
    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }

    }
    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null){
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
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

    fun onUpdate(url: String){
        val myUser = User(
            id = user.id,
            username = state.username,
            image = url

        )
        update(myUser)
    }

    fun update (user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result
    }

}