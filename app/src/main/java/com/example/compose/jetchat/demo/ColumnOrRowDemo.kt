package com.example.compose.jetchat.demo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable
// import com.example.compose.jetchat.demo.convertSpToDp

/**
 * Demo of switching from Row to Column stacking when the user chooses
 * larger font sizes in Android system settings.
 */
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
            modifier = Modifier.padding(12.dp),
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

/**
 * "ColumnOrRowDemoV2" not in use!
 */
@Composable
fun ColumnOrRowDemoV2(onClick: () -> Unit) {
    //..
    ColumnOrRow(
        modifier = Modifier.padding(12.dp),
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

/**
 * "ColumnOrRow" function does not work. The cast crashes the app.
 */
@Composable
inline fun ColumnOrRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    thresholdInDp: Int = 18,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    noinline content: @Composable RowScope.() -> Unit
): Unit {
    // Convert SP to PX to DP.
    val fontSizeInDp = convertSpToDp(textStyle.fontSize)

    val shouldVerticalStack = (fontSizeInDp > thresholdInDp)

    if (shouldVerticalStack) {
        return Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = content as @Composable (ColumnScope.() -> Unit), // @TODO: Casting doesn't work ("Unchecked cast..."). Crashes!
        )
    } else {
        return Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = verticalAlignment,
            content = content
        ) /* {
            content()
        } */
    }
}