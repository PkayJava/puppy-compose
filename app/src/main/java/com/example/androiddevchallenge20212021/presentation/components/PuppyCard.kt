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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.util.loadPuppy
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PuppyCard(index: Int, puppy: Puppy, onClick: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 5.dp,
    ) {
        ListItem(
            icon = {
                val image = loadPuppy(puppyFile = puppy.puppyFile).value
                image?.let { img ->
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Puppy Featured Image",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(color = Color.LightGray),
                        contentScale = ContentScale.Crop,
                    )
                }
            },
            text = { Text(text = puppy.name) },
            secondaryText = { Text(text = puppy.description) },
            modifier = Modifier.background(
                color = if (index % 2 == 0) Color(0xFFe0f7fa) else Color(
                    0xffe3f2fd
                )
            )
        )
    }
}
