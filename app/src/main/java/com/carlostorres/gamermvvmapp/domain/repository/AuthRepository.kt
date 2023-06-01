package com.carlostorres.gamermvvmapp.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun login()
}