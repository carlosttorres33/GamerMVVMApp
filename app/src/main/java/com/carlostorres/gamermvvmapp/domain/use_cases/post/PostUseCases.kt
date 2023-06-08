package com.carlostorres.gamermvvmapp.domain.use_cases.post

data class PostUseCases (
    val createPost : CreatePost,
    val getPosts: GetPosts,
    val getPostByIdUser: GetPostByIdUser,
    val deletePost: DeletePost,
    val updatePost: UpdatePost,
    val likePost: LikePost,
    val dislikePost: DislikePost
        )