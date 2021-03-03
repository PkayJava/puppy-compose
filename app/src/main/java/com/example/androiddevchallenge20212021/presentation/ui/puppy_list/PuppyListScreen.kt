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
package com.example.androiddevchallenge20212021.presentation.ui.puppy_list

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.example.androiddevchallenge20212021.presentation.components.PuppyList
import com.example.androiddevchallenge20212021.ui.theme.JetpackskhauvTheme
import com.example.androiddevchallenge20212021.util.TAG
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun PuppyListScreen(
    navHostController: NavHostController,
    viewModel: PuppyListViewModel,
) {
    Log.d(TAG, "PuppyListScreen: $viewModel")

    val puppies = viewModel.puppies.value

    val loading = viewModel.loading.value

    val scaffoldState = rememberScaffoldState()

    JetpackskhauvTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "Puppy Quick App") })
            },
            scaffoldState = scaffoldState,
            snackbarHost = {
                scaffoldState.snackbarHostState
            },
        ) {
            PuppyList(
                navHostController = navHostController,
                loading = loading,
                puppies = puppies,
                onChangeScrollPosition = viewModel::onChangePuppyScrollPosition,
                onTriggerNextPage = { viewModel.onTriggerEvent(PuppyListEvent.NextPageEvent) },
            )
        }
    }
}
