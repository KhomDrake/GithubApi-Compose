package com.vlv.githubapicompose.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vlv.githubapicompose.ui.screens.home.HomeViewModel
import com.vlv.githubapicompose.ui.screens.home.widget.RepositoriesList
import com.vlv.githubapicompose.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navigator: NavController, viewModel: HomeViewModel = koinViewModel()) {
    val language by remember {
        mutableStateOf("kotlin")
    }

    val data = viewModel.state

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(
                style = Typography.h1,
                text = "Home",
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Text(
                style = Typography.h3,
                text = "Language Chosen: Kotlin",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable {
                        viewModel.searchRepository(language)
                    }
            )
            RepositoriesList(repositories = data.repositories)
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}