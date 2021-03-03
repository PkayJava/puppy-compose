/*
 * Copyright 2021 The Android Open Source Project
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
package com.example.androiddevchallenge20212021.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush.Companion.linearGradient
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerPuppyCardItem(
    colors: List<Color>,
    xShimmer: Float,
    yShimmer: Float,
    cardHeight: Dp,
    gradientWidth: Float,
    padding: Dp
) {
    val brush = linearGradient(
        colors,
        start = Offset(xShimmer - gradientWidth, yShimmer - gradientWidth),
        end = Offset(xShimmer, yShimmer)
    )
    Column(modifier = Modifier.padding(padding)) {
        Surface(
            shape = MaterialTheme.shapes.small,
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight)
                    .background(brush = brush)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Surface(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(vertical = 8.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(cardHeight / 10)
                    .background(brush = brush)
            )
        }
    }
}
