package com.carlostorres.gamermvvmapp.domain.repository

import com.carlostorres.gamermvvmapp.domain.model.Response
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email:String, password:String):Response<FirebaseUser>
}