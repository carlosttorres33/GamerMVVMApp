package com.carlostorres.gamermvvmapp.presentation.screens.my_posts.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.domain.model.Post

@Composable
fun MyPostContent(
    navController: NavHostController,
    posts: List<Post>
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth().padding(bottom = 80.dp),
        userScrollEnabled = true
    ){
        items(
            items = posts
        ){ posts ->
            MyPostsCard(navController, posts)
        }
    }


}