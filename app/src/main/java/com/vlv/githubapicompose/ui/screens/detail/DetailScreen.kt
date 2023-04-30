package com.vlv.githubapicompose.ui.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(githubName: String, id: Int) {
    Text(text = "TestScreen $githubName $id")
}

