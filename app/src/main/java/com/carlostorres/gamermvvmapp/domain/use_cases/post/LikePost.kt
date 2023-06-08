package com.carlostorres.gamermvvmapp.domain.use_cases.post

import com.carlostorres.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class LikePost @Inject constructor(private val repository: PostRepository) {

    suspend operator fun invoke(idPost: String, idUser: String) = repository.like(idPost, idUser)

}