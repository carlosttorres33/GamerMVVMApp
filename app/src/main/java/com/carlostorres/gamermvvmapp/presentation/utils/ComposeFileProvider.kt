package com.carlostorres.gamermvvmapp.presentation.utils

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.net.Uri
import android.os.FileUtils
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.carlostorres.gamermvvmapp.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.UUID

class ComposeFileProvider: FileProvider(R.xml.file_paths) {

    companion object{
        fun getImageUri(context: Context) : Uri{
            val directory = File(context.cacheDir, "images")
            directory.mkdirs()
            val file = File.createTempFile(
                "selected_image_",
                ".jpg",
                directory
            )
            val authority = context.packageName + ".fileprovider"
            return getUriForFile(
                context, authority, file
            )
        }

        fun getPathFromBitmap(context: Context, bitmap: Bitmap): String{
            val wrapper = ContextWrapper(context)
            var file = wrapper.getDir("Images", Context.MODE_PRIVATE)
            file = File(file, "${UUID.randomUUID()}.jpg")
            val stream: OutputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,stream)
            stream.flush()
            stream.close()
            return file.path
        }

        fun createFileFromUri(context: Context, uri: Uri): File? {
            return try {
                val stream = context.contentResolver.openInputStream(uri)
                val file = File.createTempFile(
                    "${System.currentTimeMillis()}",
                    ".png",
                    context.cacheDir
                )
                org.apache.commons.io.FileUtils.copyInputStreamToFile(stream, file)
                return file
            }catch (e: Exception){
                e.printStackTrace()
                return null
            }
        }

    }



}