package com.carlostorres.gamermvvmapp.presentation.screens.posts

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.use_cases.post.PostUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postUseCases: PostUseCases
) :ViewModel() {

    var postResponse by mutableStateOf<Response<List<Post>>?>(null)

    init {
        getPosts()
    }

    fun getPosts() = viewModelScope.launch {

        postResponse = Response.Loading
        postUseCases.getPosts().collect(){
            postResponse = it
        }


    }

}