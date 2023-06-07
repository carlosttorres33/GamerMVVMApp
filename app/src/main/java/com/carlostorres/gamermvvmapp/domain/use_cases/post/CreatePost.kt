package com.carlostorres.gamermvvmapp.domain.use_cases.post

import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.repository.PostRepository
import java.io.File
import javax.inject.Inject

class CreatePost @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(post:Post, file: File) = repository.create(post, file)

}