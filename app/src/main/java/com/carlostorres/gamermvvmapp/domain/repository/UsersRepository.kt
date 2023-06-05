package com.carlostorres.gamermvvmapp.domain.repository

import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.model.User
import kotlinx.coroutines.flow.Flow

interface UsersRepository {

    suspend fun create(user: User): Response<Boolean>

    suspend fun update(user: User): Response<Boolean>

    fun getUserById(id: String): Flow<User>

}