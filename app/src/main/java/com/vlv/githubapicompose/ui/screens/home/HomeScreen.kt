package com.vlv.githubapicompose.ui.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.vlv.githubapicompose.ui.screens.home.widget.RepositoriesList
import com.vlv.githubapicompose.ui.screens.home.widget.Search
import com.vlv.githubapicompose.ui.screens.widget.CircularLoading
import com.vlv.githubapicompose.ui.screens.widget.DefaultError
import com.vlv.githubapicompose.ui.theme.Typography
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navigator: NavController, viewModel: HomeViewModel = koinViewModel()) {
    val language by viewModel.languageSearched.collectAsState()

    val data = viewModel.repositories.collectAsLazyPagingItems()

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
                text = "Language Chosen: $language",
                modifier = Modifier
                    .padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Search(
                hint = "Write a language",
                modifier = Modifier.fillMaxWidth(),
                onClickSearchIcon = {
                    if(viewModel.setLanguage(it)) data.refresh()
                }
            )

            when(data.loadState.refresh) {
                is LoadState.NotLoading -> {
                    RepositoriesList(
                        repositories = data,
                        onClick = {
                            navigator.navigate(
                                "detail/${it.id}"
                            )
                        }
                    )
                }
                LoadState.Loading -> {
                    CircularLoading()
                }
                else -> {
                    DefaultError(
                        text = "Tentar Novamente",
                        onClick = {
                            data.retry()
                        },
                        modifier = Modifier
                            .fillMaxSize()
                    )
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