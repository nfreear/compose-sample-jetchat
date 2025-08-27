package com.example.compose.jetchat.demo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
// import com.example.compose.jetchat.demo.convertSpToDp

@Composable
fun ColumnOrRowDemo() {
    ColumnOrRow(text = "Button One")
    ColumnOrRow(text = "Button Two")
}

@Composable
private fun ColumnOrRow(text: String) {
    // Threshold: 18.dp
    val thresholdInDp = 18
    val style = MaterialTheme.typography.bodyMedium

    // Convert SP to PX to DP.
    val fontSizeInDp = convertSpToDp(style.fontSize)

    if (fontSizeInDp > thresholdInDp) {
        Row() {
            Text(text = text)
        }
    } else {
        Column() {
            Text(text = text)
        }
    }
}
