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

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.util.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PuppyList(
    navHostController: NavHostController,
    loading: Boolean,
    puppies: List<Puppy>,
    onChangeScrollPosition: (Int) -> Unit,
    onTriggerNextPage: () -> Unit,
) {
    Box(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
        if (loading && puppies.isEmpty()) {
            LoadingPuppyListShimmer(imageHeight = 250.dp)
        } else if (puppies.isEmpty()) {
            NothingHere()
        } else {
            LazyColumn {
                itemsIndexed(items = puppies) { index, puppy ->
                    onChangeScrollPosition(index)
                    Log.i(TAG, "index $index puppies size ${puppies.size}")
                    if ((index + 1 == puppies.size) && !loading) {
                        Log.i(TAG, "Load more data")
                        onTriggerNextPage()
                    }
                    PuppyCard(
                        index = index,
                        puppy = puppy,
                        onClick = {
                            val route = "/detail/${puppy.id}"
                            navHostController.navigate(route = route)
                            // onNavigateToPuppyDetailScreen(route)
                        }
                    )
                }
            }
        }
    }
}
