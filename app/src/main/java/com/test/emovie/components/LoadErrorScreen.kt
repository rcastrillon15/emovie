package com.test.emovie.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LoadErrorScreen(value:String){
    Text(
        text = value,
        style = MaterialTheme.typography.h6.copy(
            fontSize = 32.sp,
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontWeight = FontWeight.ExtraBold
        )
    )
}
