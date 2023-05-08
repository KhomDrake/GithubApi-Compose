package com.vlv.githubapicompose.ui.screens.home.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vlv.githubapicompose.ui.theme.GithubApiTheme
import com.vlv.githubapicompose.ui.theme.PrimaryColor
import com.vlv.githubapicompose.ui.theme.SecondaryColor
import com.vlv.githubapicompose.ui.theme.Typography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Search(
    hint: String,
    modifier: Modifier = Modifier,
    initialValue: String = "",
    onValueChange: (String) -> Unit = {},
    onClickSearchIcon: (String) -> Unit = {}
) {
    var actualValue by remember {
        mutableStateOf(initialValue)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        value = actualValue,
        onValueChange = {
            actualValue = it
            onValueChange.invoke(it)
        },
        textStyle = Typography.body2,
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        modifier = modifier,
        keyboardActions = KeyboardActions {
            if(actualValue.isEmpty().not()) {
                onClickSearchIcon.invoke(actualValue)
            }
            defaultKeyboardAction(ImeAction.Done)
        },
        placeholder = {
            Text(
                text = hint,
                style = Typography.body2,
                color = Color.Gray
            )
        },
        keyboardOptions = KeyboardOptions(
            autoCorrect = true,
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        if(actualValue.isEmpty()) return@clickable
                        onClickSearchIcon.invoke(actualValue)
                        keyboardController?.hide()
                    },
                tint = Color.White
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            backgroundColor = SecondaryColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = PrimaryColor,
            focusedLabelColor = PrimaryColor
        )
    )
}

@Preview
@Composable
fun SearchPreview() {
    GithubApiTheme {
        var language by remember {
            mutableStateOf("kotlin")
        }
        Search(
            initialValue = language,
            hint = "Write a language",
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                language = it
            },
            onClickSearchIcon = {
                language += "test"
            }
        )
    }
}