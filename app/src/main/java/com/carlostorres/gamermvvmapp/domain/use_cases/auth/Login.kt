package com.carlostorres.gamermvvmapp.domain.use_cases.auth

import com.carlostorres.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.carlostorres.gamermvvmapp.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository){

    suspend operator fun invoke(email:String, password:String) =repository.login(email, password)

}