package com.carlostorres.gamermvvmapp.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.carlostorres.gamermvvmapp.domain.model.Post

@Composable
fun PostsCard(post: Post) {
    Card(
        modifier = Modifier.padding(top = 1.dp, bottom = 5.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        //colors = CardDefaults.cardColors(Color.White),
        shape = RoundedCornerShape(1.dp)
    ) {
        Column() {
            Text(
                modifier = Modifier.padding(horizontal = 5.dp),
                text = post.idUser,
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
                modifier = Modifier.padding(horizontal = 5.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

        }
    }
}
