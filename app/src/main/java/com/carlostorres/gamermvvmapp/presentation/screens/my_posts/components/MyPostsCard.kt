package com.carlostorres.gamermvvmapp.presentation.screens.my_posts.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.carlostorres.gamermvvmapp.domain.model.Post
import com.carlostorres.gamermvvmapp.presentation.navigation.DetailsScreen
import com.carlostorres.gamermvvmapp.presentation.screens.my_posts.MyPostViewModel

@Composable
fun MyPostsCard(navController: NavHostController, post: Post, viewModel: MyPostViewModel= hiltViewModel()) {
    Card(
        modifier = Modifier
            .padding(
                top = 1.dp,
                bottom = 5.dp
            )
            .clickable {
                navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = CardDefaults.cardElevation(4.dp),
        //colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(1.dp)
    ) {
        Column() {
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = post.user?.username ?: "",
                fontSize = 12.sp
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                contentScale = ContentScale.Crop,
                model = post.image,
                contentDescription = ""
            )

            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = post.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .padding(bottom = 5.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                IconButton(onClick = { navController.navigate(route = DetailsScreen.UpdatePost.passPost(post.toJson())) }) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)
                    )
                }

                IconButton(onClick = { viewModel.delete(post.id) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier.size(15.dp)
                    )
                }

            }

        }
    }
}
