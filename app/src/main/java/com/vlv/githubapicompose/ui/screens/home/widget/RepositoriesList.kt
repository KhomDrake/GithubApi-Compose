package com.vlv.githubapicompose.ui.screens.home.widget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vlv.githubapicompose.domain.data.Repository

@Composable
fun RepositoriesList(
    repositories: List<Repository>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
    ) {
        items(repositories) {
            Repository(it)
        }
    }
}