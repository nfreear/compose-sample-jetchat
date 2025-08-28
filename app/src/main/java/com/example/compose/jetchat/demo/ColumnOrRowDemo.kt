package com.example.compose.jetchat.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
// import com.example.compose.jetchat.demo.convertSpToDp

@Composable
fun ColumnOrRowDemo(onClick: () -> Unit) {
    // Threshold: 18.dp
    val thresholdInDp = 18
    val style = MaterialTheme.typography.bodyMedium

    // Convert SP to PX to DP.
    val fontSizeInDp = convertSpToDp(style.fontSize)

    if (fontSizeInDp > thresholdInDp) {
        Column( // Stack items vertically.
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = { onClick() }) {
                Text("Button One")
            }
            Button(onClick = { onClick() }) {
                Text("Button Two")
            }
        }
    } else { // Stack items horizontally.
        Row(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = { onClick() }) {
                Text("Button One")
            }
            Button(onClick = { onClick() }) {
                Text("Button Two")
            }
        }
    }
}
