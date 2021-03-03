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
package com.example.androiddevchallenge20212021.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge20212021.datastore.SettingsDataStore
import com.example.androiddevchallenge20212021.presentation.ui.puppy.PuppyDetailScreen
import com.example.androiddevchallenge20212021.presentation.ui.puppy.PuppyViewModel
import com.example.androiddevchallenge20212021.presentation.ui.puppy_list.PuppyListScreen
import com.example.androiddevchallenge20212021.presentation.ui.puppy_list.PuppyListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var settingsDataStore: SettingsDataStore

    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()
            NavHost(navController = navHostController, startDestination = "/list") {
                composable(route = "/list") { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: PuppyListViewModel = viewModel("PuppyListViewModel", factory)
                    PuppyListScreen(
                        navHostController = navHostController,
                        viewModel = viewModel,
                    )
                }
                composable(
                    route = "/detail/{puppyId}",
                    arguments = listOf(
                        navArgument("puppyId") {
                            type = NavType.IntType
                        }
                    )
                ) { navBackStackEntry ->
                    val factory = HiltViewModelFactory(LocalContext.current, navBackStackEntry)
                    val viewModel: PuppyViewModel = viewModel("PuppyDetailViewModel", factory)
                    PuppyDetailScreen(
                        puppyId = navBackStackEntry.arguments?.getInt("puppyId"),
                        viewModel = viewModel,
                    )
                }
            }
        }
    }
}
