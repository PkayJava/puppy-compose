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

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.repository.PuppyRepository
import com.example.androiddevchallenge20212021.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_PUPPY = "puppy.state.puppy.key"

@ExperimentalCoroutinesApi
@HiltViewModel
class PuppyViewModel
@Inject
constructor(
    private val repository: PuppyRepository,
    @Named("key") private val key: String,
    private val state: SavedStateHandle,
) : ViewModel() {

    val puppy: MutableState<Puppy?> = mutableStateOf(null)

    val loading = mutableStateOf(false)

    val onLoad: MutableState<Boolean> = mutableStateOf(false)

    init {
        // restore if process dies
        state.get<Int>(STATE_KEY_PUPPY)?.let { puppyId ->
            onTriggerEvent(PuppyEvent.GetPuppyEvent(puppyId))
        }
    }

    fun onTriggerEvent(event: PuppyEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is PuppyEvent.GetPuppyEvent -> {
                        if (puppy.value == null) {
                            getPuppy(event.id)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: $e, ${e.cause}")
                e.printStackTrace()
            }
        }
    }

    private fun getPuppy(id: Int) {
        repository.loadDetail(puppyId = id)
            .onEach { dataState ->
                loading.value = dataState.loading

                dataState.data?.let { data ->
                    puppy.value = data
                    state.set(STATE_KEY_PUPPY, data.id)
                }

                dataState.error?.let { error ->
                    Log.e(TAG, "getPuppy: $error")
                }
            }.launchIn(viewModelScope)
    }
}
