package com.carlostorres.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.domain.model.Post

@Composable
fun PostContent(
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
            PostsCard(navController, posts)
        }
    }


}