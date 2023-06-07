package com.carlostorres.gamermvvmapp.presentation.screens.detail_post.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.carlostorres.gamermvvmapp.R
import com.carlostorres.gamermvvmapp.presentation.screens.my_posts.new_post.components.NewPostContent
import com.carlostorres.gamermvvmapp.presentation.ui.theme.GamerMVVMAppTheme
import com.carlostorres.gamermvvmapp.presentation.ui.theme.Pink500
import okhttp3.CertificatePinner

@Composable
fun DetailPostCOntent() {

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            model = "",
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
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
                    model = "",
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(text = "Nombre del Usuario", fontSize = 13.sp)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "E-mail", fontSize = 11.sp)
                }

            }

        }

        Text(
            text = "Titulo del juego",
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
                    painter = painterResource(id = R.drawable.icon_xbox),
                    contentDescription = "",
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Categoria",
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
            text = "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            fontSize = 14.sp,
            modifier = Modifier.padding(10.dp)
        )

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun Preview() {
    GamerMVVMAppTheme(darkTheme = true) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            DetailPostCOntent()
        }
    }
}