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
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.util.DateUtils
import com.example.androiddevchallenge20212021.util.loadPuppy
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun PuppyView(puppy: Puppy) {
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        item {
            val image =
                loadPuppy(puppyFile = puppy.puppyFile).value
            image?.let { img ->
                Card(modifier = Modifier.padding(10.dp)) {
                    Image(
                        bitmap = img.asImageBitmap(),
                        contentDescription = "Puppy Featured Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(260.dp)
                            .background(color = Color.LightGray),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Card(
                modifier = Modifier.padding(10.dp), elevation = 5.dp,
                backgroundColor = Color(0xFFe0f7fa)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Name",
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = puppy.name,
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            Card(
                modifier = Modifier.padding(10.dp), elevation = 5.dp,
                backgroundColor = Color(0xFFe0f7fa)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Date Of Birth",
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = DateUtils.dateToString1(DateUtils.stringToDate(puppy.dateOfBirth)),
                        style = MaterialTheme.typography.subtitle2
                    )
                }
            }
            Card(
                modifier = Modifier.padding(10.dp), elevation = 5.dp,
                backgroundColor = Color(0xFFe0f7fa),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        text = puppy.description,
                        style = MaterialTheme.typography.body1
                    )
                }
            }
            Card(
                modifier = Modifier.padding(10.dp), elevation = 5.dp,
                backgroundColor = Color(0xFFe8f5e9),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = puppy.phrase,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}
