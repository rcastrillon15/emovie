package com.test.emovie.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun DetailTopAppBar(title:String, onBack: () -> Unit) {
    TopAppBar(
        title = { Text(text = title, color = Color.White) },
        backgroundColor = Color.Black,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    tint = Color.White,
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = title,
                )
            }
        }
    )
}