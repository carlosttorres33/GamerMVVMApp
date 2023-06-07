package com.carlostorres.gamermvvmapp.presentation.screens.my_posts

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.carlostorres.gamermvvmapp.presentation.navigation.DetailsScreen
import com.carlostorres.gamermvvmapp.presentation.screens.my_posts.new_post.components.NewPostContent

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyPostsScreen(navController: NavHostController) {

    Scaffold (
        content = {
            Text(text = "My Post Screen")
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(bottom = 70.dp),
                onClick = {
                    navController.navigate(DetailsScreen.NewPost.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "")
            }
        }
    )

}