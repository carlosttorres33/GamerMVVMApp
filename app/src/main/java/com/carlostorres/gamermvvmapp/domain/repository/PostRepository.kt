package com.carlostorres.gamermvvmapp.domain.repository

import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.model.Response
import java.io.File

interface PostRepository {

    suspend fun create(post: Post, file: File): Response<Boolean>

}