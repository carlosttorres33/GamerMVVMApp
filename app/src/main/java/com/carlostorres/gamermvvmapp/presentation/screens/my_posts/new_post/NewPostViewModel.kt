package com.carlostorres.gamermvvmapp.presentation.screens.my_posts.new_post

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postUseCases: PostUseCases,
    private val authUseCase: AuthUseCase
    ) :
    ViewModel() {

    var state by mutableStateOf(NewPostsState())

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
    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set
    val currentUser = authUseCase.getCurrentUser()

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

    fun onNewPost(){
        val post = Post(
            name = state.name,
            description = state.description,
            category = state.cat,
            idUser = currentUser?.uid ?: ""
        )
        createPost(post)
    }

    fun createPost(post : Post) = viewModelScope.launch {
        createPostResponse = Response.Loading
        val result = postUseCases.createPost(post, file!!)
        createPostResponse = result
    }

    fun clearForm(){
        state = state.copy(
            name = "",
            description = "",
            image = "",
            cat = ""
        )
    }

}

data class CategoryRadioButton(
    var cat: String,
    var img: Int
)