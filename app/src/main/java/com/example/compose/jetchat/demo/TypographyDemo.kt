package com.example.compose.jetchat.demo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
// import com.example.compose.jetchat.demo.convertSpToDp

@Composable
fun TypographyDemo() {
    // DrawerItemHeader("Typography - Font Size")
    TypographyItem(
        text = "displayMedium: 45sp",
        style = MaterialTheme.typography.displayMedium
    )
    TypographyItem(
        text = "headlineLarge: 32sp",
        style = MaterialTheme.typography.headlineLarge
    )
    TypographyItem(
        text = "headlineSmall: 24sp",
        style = MaterialTheme.typography.headlineSmall
    )
    TypographyItem(
        text = "bodyMedium: 14sp",
        style = MaterialTheme.typography.bodyMedium
    )
    TypographyItem(
        text = "labelSmall: 11sp",
        style = MaterialTheme.typography.labelSmall
    )
}

@Composable
fun TypoDemoV2() {
    // DrawerItemHeader("Typography - Font Size, V2")
    TypoRowV2(10.sp)
    TypoRowV2(20.sp)
    TypoRowV2(30.sp)
    TypoRowV2(40.sp)
    TypoRowV2(50.sp)
    TypoRowV2(60.sp)
    TypoRowV2(70.sp)
}

@Composable
private fun TypoRowV2(sizeInSp: TextUnit) {
    // Convert SP to PX to DP.
    val fontSizeInDp = convertSpToDp(sizeInSp)

    Row() {
        Text(
            text = "$sizeInSp -> ${fontSizeInDp}.dp"
        )
    }
}

@Composable
private fun TypographyItem(text: String, style: TextStyle) { //, rowHeight: Dp) {
    val background = /* if (selected) {
        Modifier.background(MaterialTheme.colorScheme.primaryContainer)
    } else { */
        Modifier
    // }

    // Convert SP to PX to DP.
    val fontSizeInDp = convertSpToDp(style.fontSize)

    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = 56.dp)
            // .height(rowHeight) // 56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .then(background),
        // .clickable(onClick = onProfileClicked),
        verticalAlignment = CenterVertically,
    ) {
        Text(
            text = "$text > ${fontSizeInDp}dp",
            style = style, // MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}