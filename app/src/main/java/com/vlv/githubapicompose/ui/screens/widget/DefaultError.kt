package com.vlv.githubapicompose.ui.screens.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.vlv.githubapicompose.ui.theme.Typography

@Composable
fun DefaultError(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .background(Color.White)
                .border(BorderStroke(2.dp, Color.Gray))
                .padding(vertical = 8.dp, horizontal = 8.dp)
                .clickable {
                    onClick.invoke()
                },
            text = text,
            style = Typography.button
        )
    }
}