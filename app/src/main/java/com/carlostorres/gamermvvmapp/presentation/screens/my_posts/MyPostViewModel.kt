package com.carlostorres.gamermvvmapp.presentation.screens.my_posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.AuthUseCase
import com.carlostorres.gamermvvmapp.domain.use_cases.post.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPostViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val authUseCase: AuthUseCase
) :ViewModel() {

    var postResponse by mutableStateOf<Response<List<Post>>?>(null)
    var deleteResponse by mutableStateOf<Response<Boolean>?>(null)
    val currentUser = authUseCase.getCurrentUser()

    init {
        getPosts()
    }

    fun delete(idPost:String)= viewModelScope.launch {
        deleteResponse = Response.Loading
        val result = postUseCases.deletePost(idPost)
        deleteResponse = result
    }

    fun getPosts() = viewModelScope.launch {

        postResponse = Response.Loading
        postUseCases.getPostByIdUser(currentUser?.uid ?: "").collect(){
            postResponse = it
        }


    }

}