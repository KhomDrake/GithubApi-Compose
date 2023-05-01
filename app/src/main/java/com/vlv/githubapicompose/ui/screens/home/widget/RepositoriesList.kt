package com.vlv.githubapicompose.ui.screens.home.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
    onClick: (Repository) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .padding(top = 8.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(repositories) {
            val model = it
            Repository(
                model,
                modifier = Modifier
                    .clickable {
                        onClick.invoke(model)
                    }
            )
        }
    }
}