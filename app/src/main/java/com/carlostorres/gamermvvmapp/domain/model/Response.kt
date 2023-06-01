package com.carlostorres.gamermvvmapp.domain.model

import java.lang.Exception

sealed interface Response<out T>{
    object Loading: Response<Nothing>{}
    data class Succes<out T> (val data: T): Response<T>{}
    data class Faliure<out T> (val exception: Exception): Response<T>{}
}