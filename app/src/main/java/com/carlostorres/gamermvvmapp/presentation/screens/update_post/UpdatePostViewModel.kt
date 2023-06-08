package com.carlostorres.gamermvvmapp.presentation.screens.update_post

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.AuthUseCase
import com.carlostorres.gamermvvmapp.domain.use_cases.post.PostUseCases
import com.carlostorres.gamermvvmapp.presentation.utils.ComposeFileProvider
import com.carlostorres.gamermvvmapp.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UpdatePostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val savedStateHandle: SavedStateHandle,
    private val postUseCases: PostUseCases,
    private val authUseCase: AuthUseCase
    ) :
    ViewModel() {

    var state by mutableStateOf(UpdatePostState())

    val radioOptions = listOf(
        CategoryRadioButton("PC", R.drawable.icon_pc),
        CategoryRadioButton("PS4", R.drawable.icon_ps4),
        CategoryRadioButton("X-Box", R.drawable.icon_xbox),
        CategoryRadioButton("Nintendo", R.drawable.icon_nintendo),
        CategoryRadioButton("Móvil", R.drawable.icon_pc),
    )

    //File Image
    var file: File? = null
    val resultingActivityHandler = ResultingActivityHandler()

    ////Post
    var updatePostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    val currentUser = authUseCase.getCurrentUser()

    //Recuperar argumentos que ya existen en el POST
    val data = savedStateHandle.get<String>("post")
    val post = Post.fromJson(data!!)

    init {
        state=state.copy(name = post.name, description = post.description, image = post.image, cat = post.category)
    }

    fun onNameInput(name: String) {
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String) {
        state = state.copy(cat = category)
    }

    fun onDescriptionInput(description: String) {
        state = state.copy(description = description)
    }

    fun onImageInput(img: String) {
        state = state.copy(image = img)
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }

    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)
        }
    }

    fun onUpdatePost(){
        val post = Post(
            id = post.id,
            name = state.name,
            description = state.description,
            category = state.cat,
            image = post.image,
            idUser = currentUser?.uid ?: ""
        )
        updatePost(post)
    }

    fun updatePost(post : Post) = viewModelScope.launch {
        updatePostResponse = Response.Loading
        val result = postUseCases.updatePost(post, file)
        updatePostResponse = result
    }

    fun clearForm(){
        /*state = state.copy(
            name = "",
            description = "",
            image = "",
            cat = ""
        )*/

        updatePostResponse = null

    }

}

data class CategoryRadioButton(
    var cat: String,
    var img: Int
)