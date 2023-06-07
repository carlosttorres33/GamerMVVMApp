package com.carlostorres.gamermvvmapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.carlostorres.gamermvvmapp.presentation.screens.detail_post.DetailPostScreen
import com.carlostorres.gamermvvmapp.presentation.screens.my_posts.new_post.NewPostScreen
import com.carlostorres.gamermvvmapp.presentation.screens.profile_edit.ProfileEditScreen

fun NavGraphBuilder.detailsNavGraph(navController: NavHostController){

    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.ProfileEdit.route
    ){
        composable(
            route = DetailsScreen.NewPost.route
        ){
            NewPostScreen(navController)
        }

        composable(
            route = DetailsScreen.ProfileEdit.route,
            arguments = listOf(navArgument("user"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("user")?.let { user ->
                ProfileEditScreen(navController, user = user)
            }
        }

        composable(
            route = DetailsScreen.DetailPost.route,
            arguments = listOf(navArgument("post"){
                type = NavType.StringType
            })
        ){
            it.arguments?.getString("post")?.let { it ->
                DetailPostScreen(navController = navController, post = it)
            }
        }


    }

}

sealed class DetailsScreen(val route: String){

    object NewPost: DetailsScreen("post/new")

    object ProfileEdit: DetailsScreen("profile/edit/{user}"){
        fun passUser(user: String) = "profile/edit/${user}"
    }

    object DetailPost: DetailsScreen("posts/detail/{post}"){
        fun passPost(post: String) = "posts/detail/${post}"
    }

}