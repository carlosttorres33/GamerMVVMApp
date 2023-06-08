package com.carlostorres.gamermvvmapp.domain.use_cases.post

import com.carlostorres.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPostByIdUser @Inject constructor(private val repository: PostRepository) {

    operator fun invoke(idUser: String) = repository.getPostByUserId(idUser)

}