package com.carlostorres.gamermvvmapp.data.repository

import android.net.Uri
import com.carlostorres.gamermvvmapp.core.Constants.POST
import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.domain.model.Response
import com.carlostorres.gamermvvmapp.domain.repository.PostRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostRepoImpl @Inject constructor(
    @Named(POST) private val postRef: CollectionReference,
    @Named(POST) private val storagePostRef: StorageReference,
) : PostRepository {

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