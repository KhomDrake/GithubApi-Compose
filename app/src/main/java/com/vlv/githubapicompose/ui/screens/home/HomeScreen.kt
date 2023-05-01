package com.vlv.githubapicompose.ui.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vlv.githubapicompose.domain.data.StateResponse
import com.vlv.githubapicompose.ui.screens.home.widget.RepositoriesList
import com.vlv.githubapicompose.ui.screens.widget.CircularLoading
import com.vlv.githubapicompose.ui.screens.widget.DefaultError
import com.vlv.githubapicompose.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navigator: NavController, viewModel: HomeViewModel = koinViewModel()) {
    val language by remember {
        mutableStateOf("kotlin")
    }

    val data = viewModel.state

    LaunchedEffect(key1 = language, block = {
        viewModel.searchRepository(language)
    })

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
            )

            when(data.state) {
                StateResponse.SUCCESS -> {
                    RepositoriesList(
                        repositories = data.data ?: listOf(),
                        onClick = {
                            navigator.navigate(
                                "detail/${it.id}"
                            )
                        }
                    )
                }
                StateResponse.ERROR -> {
                    DefaultError(
                        text = "Tentar Novamente",
                        onClick = {
                            viewModel.searchRepository(language)
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                else -> {
                     CircularLoading()
                }
            }
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