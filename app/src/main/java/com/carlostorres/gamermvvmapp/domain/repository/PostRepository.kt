package com.carlostorres.gamermvvmapp.domain.repository

import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    fun getPost(): Flow<Response<List<Post>>>

    suspend fun create(post: Post, file: File): Response<Boolean>

}