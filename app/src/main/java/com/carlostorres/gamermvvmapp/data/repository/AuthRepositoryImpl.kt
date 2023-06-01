package com.carlostorres.gamermvvmapp.data.repository

import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth): AuthRepository {

    override val currentUser: FirebaseUser? get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String): Response<FirebaseUser> {

        return try {
            val result = firebaseAuth.signInWithEmailAndPassword(email,password).await()
            Response.Succes(result.user!!)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Faliure(e)
        }

    }

}