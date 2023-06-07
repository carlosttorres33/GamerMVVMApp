package com.carlostorres.gamermvvmapp.domain.model

data class Post(
    val id: String ="",
    var name: String="",
    var description: String="",
    var category: String="",
    val idUser: String="",
    var image: String =""
)
