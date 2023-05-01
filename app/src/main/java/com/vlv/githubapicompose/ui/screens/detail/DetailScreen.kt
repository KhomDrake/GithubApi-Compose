package com.vlv.githubapicompose.ui.screens.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.vlv.githubapicompose.domain.data.StateResponse
import com.vlv.githubapicompose.ui.screens.detail.widget.DetailData
import com.vlv.githubapicompose.ui.screens.detail.widget.ShimmerDetail
import com.vlv.githubapicompose.ui.screens.widget.DefaultError
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailScreen(id: Int, detailViewModel: DetailViewModel = koinViewModel()) {
    val data = detailViewModel.detailData
    LaunchedEffect(key1 = id, block = {
        detailViewModel.loadRepositoryData(id)
    })

    when(data.state) {
        StateResponse.SUCCESS -> {
            data.data?.let { DetailData(repository = it) }
        }
        StateResponse.ERROR -> {
            DefaultError(
                text = "Tentar Novamente",
                onClick = { detailViewModel.loadRepositoryData(id) },
                modifier = Modifier.fillMaxSize()
            )
        }
        else -> {
            ShimmerDetail()
        }
    }


}

