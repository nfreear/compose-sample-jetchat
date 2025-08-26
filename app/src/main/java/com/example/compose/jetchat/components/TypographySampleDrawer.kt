/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.jetchat.components

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.TextUnit
import android.util.TypedValue
import android.util.DisplayMetrics
import com.example.compose.jetchat.R
import com.example.compose.jetchat.data.colleagueProfile
import com.example.compose.jetchat.data.meProfile
import com.example.compose.jetchat.theme.JetchatTheme
import com.example.compose.jetchat.widget.WidgetReceiver

// Based on: `components/JetchatDrawer.kt` ~ `JetchatDrawerContent()`
@Composable
fun TypographySampleDrawerContent(onProfileClicked: (String) -> Unit, onChatClicked: (String) -> Unit, selectedMenu: String = "composers") {
    // Use windowInsetsTopHeight() to add a spacer which pushes the drawer content
    // below the status bar (y-axis)
    Column {
        Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
        DrawerHeader()
        DividerItem()

        // TypographyDemo()

        TypoDemoV2()

        DividerItem()

        RowOrColumnDemo()

        if (widgetAddingIsSupported(LocalContext.current)) {
            DividerItem(modifier = Modifier.padding(horizontal = 28.dp))
            DrawerItemHeader("Settings")
            WidgetDiscoverability()
        }
    }
}

@Composable
private fun TypographyDemo() {
    DrawerItemHeader("Typography - Font Size")
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
private fun TypoDemoV2() {
    DrawerItemHeader("Typography - Font Size, V2")
    TypoRowV2(10.sp)
    TypoRowV2(20.sp)
    TypoRowV2(30.sp)
    TypoRowV2(40.sp)
    TypoRowV2(50.sp)
    TypoRowV2(60.sp)
    TypoRowV2(70.sp)
}

@Composable
private fun DrawerHeader() {
    Row(modifier = Modifier.padding(16.dp), verticalAlignment = CenterVertically) {
        JetchatIcon(
            contentDescription = null,
            modifier = Modifier.size(24.dp),
        )
        Image(
            painter = painterResource(id = R.drawable.jetchat_logo),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}

@Composable
private fun DrawerItemHeader(text: String) {
    Box(
        modifier = Modifier
            .heightIn(min = 52.dp)
            .padding(horizontal = 28.dp),
        contentAlignment = CenterStart,
    ) {
        Text(
            text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun TypoRowV2(sizeInSp: TextUnit) {
    // Convert SP to PX to DP.
    val fontSizeInDp = convertSpToDp(sizeInSp)

    Row() {
        Text(
            text = "${sizeInSp} -> ${fontSizeInDp}.dp"
        )
    }
}

@Composable
private fun RowOrColumnDemo() {
    RowOrColumn(text = "Button One")
    RowOrColumn(text = "Button Two")
}

@Composable
private fun RowOrColumn(text: String) {
    // Threshold: 18.dp
    val thresholdDp = 18
    val style = MaterialTheme.typography.bodyMedium

    // Convert SP to PX to DP.
    val fontSizeInDp = convertSpToDp(style.fontSize)

    if (fontSizeInDp > thresholdDp) {
        Column() {
            Text(text = text)
        }
    } else {
        Row() {
            Text(text = text)
        }
    };
}

// Convert Scale-independent pixels (sp) to Device-independent pixels (dp).
@Composable
private fun convertSpToDp(sizeInSp: TextUnit): Float {
    val context = LocalContext.current
    val displayMetrics = context.getResources().getDisplayMetrics()

    // Convert SP to PX to DP.
    val fontSizeInPx = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        sizeInSp.value,
        displayMetrics
    )
    val fontSizeInDp = TypedValue.deriveDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        fontSizeInPx,
        displayMetrics
    )
    return fontSizeInDp
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
        val paddingSizeModifier = Modifier
            .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
            .size(24.dp)
        Text(
            text = "$text > ${fontSizeInDp}dp",
            style = style, // MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun WidgetDiscoverability() {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
            .clip(CircleShape)
            .clickable(onClick = {
                addWidgetToHomeScreen(context)
            }),
        verticalAlignment = CenterVertically,
    ) {
        Text(
            stringResource(id = R.string.add_widget_to_home_page),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(start = 12.dp),
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
private fun addWidgetToHomeScreen(context: Context) {
    val appWidgetManager = AppWidgetManager.getInstance(context)
    val myProvider = ComponentName(context, WidgetReceiver::class.java)
    if (widgetAddingIsSupported(context)) {
        appWidgetManager.requestPinAppWidget(myProvider, null, null)
    }
}

@ChecksSdkIntAtLeast(api = Build.VERSION_CODES.O)
private fun widgetAddingIsSupported(context: Context): Boolean {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O &&
        AppWidgetManager.getInstance(context).isRequestPinAppWidgetSupported
}
