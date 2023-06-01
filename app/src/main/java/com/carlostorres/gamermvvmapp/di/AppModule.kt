package com.carlostorres.gamermvvmapp.di

import com.carlostorres.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.carlostorres.gamermvvmapp.domain.repository.AuthRepository
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.AuthUseCase
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.Login
import com.carlostorres.gamermvvmapp.presentation.navigation.AppScreen
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl):AuthRepository = impl

    @Provides
    fun provideAuthCases(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository)
    )


}