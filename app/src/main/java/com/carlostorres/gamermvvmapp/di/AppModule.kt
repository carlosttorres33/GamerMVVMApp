package com.carlostorres.gamermvvmapp.di

import com.carlostorres.gamermvvmapp.core.Constants.POST
import com.carlostorres.gamermvvmapp.core.Constants.USERS
import com.carlostorres.gamermvvmapp.data.repository.AuthRepositoryImpl
import com.carlostorres.gamermvvmapp.data.repository.PostRepoImpl
import com.carlostorres.gamermvvmapp.data.repository.UsersRepoImpl
import com.carlostorres.gamermvvmapp.domain.repository.AuthRepository
import com.carlostorres.gamermvvmapp.domain.repository.PostRepository
import com.carlostorres.gamermvvmapp.domain.repository.UsersRepository
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.AuthUseCase
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.GetCurrentUser
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.Login
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.Logout
import com.carlostorres.gamermvvmapp.domain.use_cases.auth.SingUp
import com.carlostorres.gamermvvmapp.domain.use_cases.post.CreatePost
import com.carlostorres.gamermvvmapp.domain.use_cases.post.PostUseCases
import com.carlostorres.gamermvvmapp.domain.use_cases.users.Create
import com.carlostorres.gamermvvmapp.domain.use_cases.users.GetUserById
import com.carlostorres.gamermvvmapp.domain.use_cases.users.SaveImage
import com.carlostorres.gamermvvmapp.domain.use_cases.users.Update
import com.carlostorres.gamermvvmapp.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Named(USERS)
    fun providesUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl):AuthRepository = impl

    @Provides
    fun providesUserRepository(impl: UsersRepoImpl): UsersRepository = impl

    @Provides
    fun provideAuthCases(repository: AuthRepository) = AuthUseCase(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        singUp = SingUp(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UsersRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Provides
    @Named(USERS)
    fun provideStorageUserRed(storage: FirebaseStorage):StorageReference = storage.reference.child(USERS)

    @Provides
    @Named(POST)
    fun providesPostRef(db: FirebaseFirestore): CollectionReference = db.collection(POST)

    @Provides
    @Named(POST)
    fun provideStoragePostRed(storage: FirebaseStorage):StorageReference = storage.reference.child(POST)

    @Provides
    fun providePostRepository(impl: PostRepoImpl): PostRepository = impl

    @Provides
    fun providePostUseCases(repository: PostRepository) = PostUseCases(
        createPost = CreatePost(repository)
    )

}