package com.carlostorres.gamermvvmapp.domain.use_cases.auth

data class AuthUseCase (
    val getCurrentUser: GetCurrentUser,
    val login: Login
)