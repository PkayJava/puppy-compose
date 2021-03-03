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
package com.example.androiddevchallenge20212021.presentation.ui.puppy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge20212021.presentation.components.IMAGE_HEIGHT
import com.example.androiddevchallenge20212021.presentation.components.LoadingPuppyShimmer
import com.example.androiddevchallenge20212021.presentation.components.PuppyView
import com.example.androiddevchallenge20212021.ui.theme.JetpackskhauvTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun PuppyDetailScreen(
    puppyId: Int?,
    viewModel: PuppyViewModel,
) {
    if (puppyId == null) {
        TODO("Show Invalid Puppy")
    } else {
        val onLoad = viewModel.onLoad.value
        if (!onLoad) {
            viewModel.onLoad.value = true
            viewModel.onTriggerEvent(PuppyEvent.GetPuppyEvent(puppyId))
        }

        val loading = viewModel.loading.value

        val puppy = viewModel.puppy.value

        val scaffoldState = rememberScaffoldState()

        JetpackskhauvTheme {
            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    scaffoldState.snackbarHostState
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (loading && puppy == null) {
                        LoadingPuppyShimmer(imageHeight = IMAGE_HEIGHT.dp)
                    } else if (!loading && puppy == null && onLoad) {
                        TODO("Show Invalid Puppy")
                    } else {
                        puppy?.let { PuppyView(puppy = it) }
                    }
                }
            }
        }
    }
}
