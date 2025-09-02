

/*
 * Copyright 2023 The Android Open Source Project
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

@file:Suppress("UNUSED_VARIABLE")

package com.example.compose.jetchat.demo
// package com.example.compose.snippets.layouts

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId

@Composable
fun HorizVertConstraintDemoV3 () {
    val constraintSet = decoupledHorizVertConstraints()

    ConstraintLayout(
        constraintSet = constraintSet,
        modifier = Modifier.padding(12.dp).fillMaxWidth(),
    ) {
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.layoutId("widget_A")
        ) {
            Text("Button One")
        }
        Button(
            onClick = { /* Do something */ },
            modifier = Modifier.layoutId("widget_B")
        ) {
            Text("Button Two")
        }
    }
}

@Composable
private fun decoupledHorizVertConstraints (
    chainStyle: ChainStyle = ChainStyle.Spread,
    thresholdInDp: Int = 18,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium
): ConstraintSet {
    val fontSizeInDp = convertSpToDp(textStyle.fontSize)
    val shouldVerticalStack = (fontSizeInDp > thresholdInDp)

    return ConstraintSet {
        val widget_A = createRefFor("widget_A")
        val widget_B = createRefFor("widget_B")

        if (shouldVerticalStack) {
            createVerticalChain(widget_A, widget_B, chainStyle = chainStyle)
        } else {
            createHorizontalChain(widget_A, widget_B, chainStyle = chainStyle)
        }
    }
}

// [START android_compose_constraintlayout_basics]
@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        // Create references for the composables to constrain
        val (button, text) = createRefs()

        Button(
            onClick = { /* Do something */ },
            // Assign reference "button" to the Button composable
            // and constrain it to the top of the ConstraintLayout
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button")
        }

        // Assign reference "text" to the Text composable
        // and constrain it to the bottom of the Button composable
        Text(
            "Text",
            Modifier.constrainAs(text) {
                top.linkTo(button.bottom, margin = 16.dp)
            }
        )
    }
}
// [END android_compose_constraintlayout_basics]

// [START android_compose_constraintlayout_decoupled]
@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp) // Portrait constraints
        } else {
            decoupledConstraints(margin = 32.dp) // Landscape constraints
        }

        ConstraintLayout(constraints) {
            Button(
                onClick = { /* Do something */ },
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin)
        }
    }
}
// [END android_compose_constraintlayout_decoupled]

@Composable
private fun ConstraintLayoutGuidelines() {
    // [START android_compose_constraintlayout_guidelines]
    ConstraintLayout {
        // Create guideline from the start of the parent at 10% the width of the Composable
        val startGuideline = createGuidelineFromStart(0.1f)
        // Create guideline from the end of the parent at 10% the width of the Composable
        val endGuideline = createGuidelineFromEnd(0.1f)
        //  Create guideline from 16 dp from the top of the parent
        val topGuideline = createGuidelineFromTop(16.dp)
        //  Create guideline from 16 dp from the bottom of the parent
        val bottomGuideline = createGuidelineFromBottom(16.dp)
    }
    // [END android_compose_constraintlayout_guidelines]
}

@Composable
private fun ConstraintLayoutBarrier() {
    // [START android_compose_constraintlayout_barrier]
    ConstraintLayout {
        val constraintSet = ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")

            val topBarrier = createTopBarrier(button, text)
        }
    }
    // [END android_compose_constraintlayout_barrier]
}

@Composable
private fun ConstraintLayoutChain() {
    // [START android_compose_constraintlayout_chain]
    ConstraintLayout {
        val constraintSet = ConstraintSet {
            val button = createRefFor("button")
            val text = createRefFor("text")

            val verticalChain = createVerticalChain(button, text, chainStyle = ChainStyle.Spread)
            val horizontalChain = createHorizontalChain(button, text)
        }
    }
    // [END android_compose_constraintlayout_chain]
}