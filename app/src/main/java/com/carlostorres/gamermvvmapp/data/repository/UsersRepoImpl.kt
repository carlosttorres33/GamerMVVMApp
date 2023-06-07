package com.carlostorres.gamermvvmapp.data.repository

import android.net.Uri
import com.carlostorres.gamermvvmapp.core.Constants
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.repository.UsersRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class UsersRepoImpl @Inject constructor(
    @Named(Constants.USERS) private val usersRef: CollectionReference,
    @Named(Constants.USERS) private val storageUsersRef: StorageReference
) : UsersRepository {

    ///Sin inyección de dependencias
    /*val firestore = Firebase.firestore
    val usersRef = firestore.collection("Users")*/

    override suspend fun create(user: User): Response<Boolean> {
        return try {
            user.password = ""
            usersRef.document(user.id).set(user).await()
            Response.Succes(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Faliure(e)
        }
    }

    override suspend fun update(user: User): Response<Boolean> {
        return try {
            val map: MutableMap<String, Any> = HashMap()
            map["username"] = user.username
            map["image"] = user.image
            usersRef.document(user.id).update(map).await()
            Response.Succes(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Faliure(e)
        }
    }

    override suspend fun saveImage(file: File): Response<String> {
        return try {

            val fromFile = Uri.fromFile(file)
            val ref = storageUsersRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()
            return Response.Succes(url.toString())

        } catch (e: Exception) {

            e.printStackTrace()
            Response.Faliure(e)

        }
    }

    override fun getUserById(id: String): Flow<User> = callbackFlow {
        val snapshotListener = usersRef.document(id).addSnapshotListener { snapshot, e ->

            val user = snapshot?.toObject(User::class.java) ?: User()
            trySend(user)

        }

        awaitClose {
            snapshotListener.remove()
        }
    }
}