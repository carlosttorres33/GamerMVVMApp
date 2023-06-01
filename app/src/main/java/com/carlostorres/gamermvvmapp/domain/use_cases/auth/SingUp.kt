package com.carlostorres.gamermvvmapp.domain.use_cases.auth

import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class SingUp @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.singUp(user)

}