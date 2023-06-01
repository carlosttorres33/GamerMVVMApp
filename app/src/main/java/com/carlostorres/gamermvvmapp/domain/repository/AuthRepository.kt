package com.carlostorres.gamermvvmapp.domain.repository

import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.model.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email:String, password:String):Response<FirebaseUser>

    fun logout()

    suspend fun singUp(user: User): Response<FirebaseUser>
}