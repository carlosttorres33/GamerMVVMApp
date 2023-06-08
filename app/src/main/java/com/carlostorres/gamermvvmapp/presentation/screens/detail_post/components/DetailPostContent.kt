package com.carlostorres.gamermvvmapp.presentation.screens.detail_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.screens.detail_post.DetailPostViewModel
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500

@Composable
fun DetailPostCOntent(
    navController: NavHostController,
    viewModel: DetailPostViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {

        Box() {

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                model = viewModel.post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )

            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(35.dp)
                )
            }

        }

        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .padding(10.dp)
                .height(80.dp)
                .fillMaxWidth()
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
            ) {
                AsyncImage(
                    modifier = Modifier
                        .height(60.dp)
                        .width(80.dp)
                        .clip(CircleShape)
                        .padding(horizontal = 10.dp),
                    model = viewModel.post.user?.image ?: "",
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(text = viewModel.post.user?.username ?: "", fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = viewModel.post.user?.email ?: "", fontSize = 11.sp)
                }

            }

        }

        Text(
            text = viewModel.post.name,
            fontSize = 20.sp,
            color = Pink500,
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .padding(top = 5.dp, bottom = 15.dp)
        )
        Card(
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(Pink500),
            modifier = Modifier.padding(horizontal = 10.dp)
        ) {

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(5.dp)
            ) {
                Image(
                    painter = painterResource(
                        id = when (viewModel.post.category) {
                            "PC" -> {
                                R.drawable.icon_pc
                            }

                            "PS4" -> {
                                R.drawable.icon_ps4
                            }

                            "X-Box" -> {
                                R.drawable.icon_xbox
                            }

                            "Nintendo" -> {
                                R.drawable.icon_nintendo
                            }

                            "Móvil" -> {
                                R.drawable.icon_pc
                            }

                            else -> {
                                R.drawable.icon_pc
                            }
                        }
                        //if (viewModel.post.category == "PC") R.drawable.icon_pc

                    ),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = viewModel.post.category,
                    fontWeight = FontWeight.Bold
                )

            }

        }

        Divider(
            thickness = 1.dp,
            modifier = Modifier
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
        )

        Text(
            text = "Descripción",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Text(
            text = viewModel.post.description,
            fontSize = 14.sp,
            modifier = Modifier.padding(10.dp)
        )

    }

}

