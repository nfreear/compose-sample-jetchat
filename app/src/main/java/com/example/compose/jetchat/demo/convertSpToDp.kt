package com.example.compose.jetchat.demo

import android.annotation.SuppressLint
import android.os.Build
import android.util.TypedValue
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.TextUnit

// Convert Scale-independent pixels (sp) to Device-independent pixels (dp).
@SuppressLint("LocalContextResourcesRead")
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun convertSpToDp(sizeInSp: TextUnit): Float {
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
