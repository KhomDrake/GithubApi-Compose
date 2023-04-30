package com.vlv.githubapicompose.ui.screens.home.widget

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.vlv.githubapicompose.domain.data.Repository
import com.vlv.githubapicompose.ui.theme.Typography

@Composable
fun Repository(repository: Repository) {
    Card(
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 16.dp)
        ) {
            Row {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(repository.url)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Owner Repository",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp)
                )
                Text(
                    text = repository.name,
                    style = Typography.h2,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp),
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "Author Name: ${repository.authorName}",
                style = Typography.h2,
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Text(
                text = "Stars: ${repository.stars}",
                style = Typography.h2,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}