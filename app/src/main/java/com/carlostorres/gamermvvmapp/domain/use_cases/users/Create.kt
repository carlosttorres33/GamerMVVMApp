package com.carlostorres.gamermvvmapp.domain.use_cases.users

import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.repository.UsersRepository
import javax.inject.Inject

class Create @Inject constructor(private val repository: UsersRepository) {

    suspend operator fun invoke(user: User) = repository.create(user)

}