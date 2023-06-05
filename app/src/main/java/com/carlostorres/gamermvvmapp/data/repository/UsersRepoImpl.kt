package com.carlostorres.gamermvvmapp.data.repository

import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UsersRepoImpl @Inject constructor(private val usersRef: CollectionReference): UsersRepository {

    ///Sin inyección de dependencias
    /*val firestore = Firebase.firestore
    val usersRef = firestore.collection("Users")*/

    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password=""
            usersRef.document(user.id).set(user).await()
            Response.Succes(true)
        }catch (e: Exception){
            e.printStackTrace()
            Response.Faliure(e)
        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow{
        val snapshotListener = usersRef.document(id).addSnapshotListener{ snapshot, e ->

            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)

        }

        awaitClose{
            snapshotListener.remove()
        }
    }
}