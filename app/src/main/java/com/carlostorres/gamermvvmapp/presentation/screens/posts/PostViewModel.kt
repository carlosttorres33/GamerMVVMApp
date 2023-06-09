package com.carlostorres.gamermvvmapp.presentation.screens.posts

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
class PostViewModel @Inject constructor(
    private val postUseCases: PostUseCases,
    private val authUseCases: AuthUseCase
) :ViewModel() {

    var postResponse by mutableStateOf<Response<List<Post>>?>(null)
    var likePostResponse by mutableStateOf<Response<Boolean>?>(null)
    var dislkePostResponse by mutableStateOf<Response<Boolean>?>(null)
    var currentUser = authUseCases.getCurrentUser

    init {
        getPosts()
    }

    fun like(idPost:String, idUser:String) = viewModelScope.launch {
        likePostResponse = Response.Loading
        val result = postUseCases.likePost(idPost,idUser)
        likePostResponse=result
    }
    fun dislike(idPost:String, idUser:String) = viewModelScope.launch {
        dislkePostResponse = Response.Loading
        val result = postUseCases.dislikePost(idPost, idUser)
        dislkePostResponse=result
    }

    fun getPosts() = viewModelScope.launch {

        postResponse = Response.Loading
        postUseCases.getPosts().collect(){
            postResponse = it
        }


    }

}