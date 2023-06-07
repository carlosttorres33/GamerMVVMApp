package com.carlostorres.gamermvvmapp.data.repository

import android.net.Uri
import android.provider.Telephony.Carriers.USER
import com.carlostorres.gamermvvmapp.core.Constants.POST
import com.carlostorres.gamermvvmapp.core.Constants.USERS
import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.model.User
import com.carlostorres.gamermvvmapp.domain.repository.PostRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepoImpl @Inject constructor(
    @Named(POST) private val postRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POST) private val storagePostRef: StorageReference,
) : PostRepository {
    override fun getPost(): Flow<Response<List<Post>>>  = callbackFlow {

        val snapshotListener = postRef.addSnapshotListener{ snapshot, e ->

            GlobalScope.launch (Dispatchers.IO) {

                val postResponse = if (snapshot != null){
                    val posts = snapshot.toObjects(Post::class.java)

                    val idUserArray = ArrayList<String>()
                    posts.forEach{ post ->
                        idUserArray.add(post.idUser)
                    }

                    val idUserList = idUserArray.toSet().toList() //Elementos sin repetir

                    idUserList.map { idUser ->
                        async {
                            val user = usersRef.document(idUser).get().await().toObject(User::class.java)!!
                            posts.forEach{ post ->
                                if (post.idUser == idUser){
                                    post.user = user
                                }
                            }
                        }
                    }.forEach {
                        it.await()
                    }

                    Response.Succes(posts)
                }else{
                    Response.Faliure(e!!)
                    /////////////////////////// Posible Bug
                }

                trySend(postResponse)

            }

        }

        awaitClose{
            snapshotListener.remove()
        }

    }

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {

            //DATA DEL POST
            val fromFile = Uri.fromFile(file)
            val ref = storagePostRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            //DATOS DEL POST
            post.image = url.toString()
            postRef.add(post).await()
            Response.Succes(true)

        }catch (e: Exception){
            e.printStackTrace()
            Response.Faliure(e)
        }
    }


}