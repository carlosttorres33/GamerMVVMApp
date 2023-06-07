package com.carlostorres.gamermvvmapp.domain.use_cases.post

import com.carlostorres.gamermvvmapp.domain.repository.PostRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val repository: PostRepository) {

    operator fun invoke() = repository.getPost()

}