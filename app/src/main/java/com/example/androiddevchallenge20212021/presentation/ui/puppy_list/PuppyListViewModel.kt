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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.repository.PuppyRepository
import com.example.androiddevchallenge20212021.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

const val STATE_KEY_PAGE = "puppy.state.page.key"
const val STATE_KEY_QUERY = "puppy.state.query.key"
const val STATE_KEY_LIST_POSITION = "puppy.state.query.list_position"

@HiltViewModel
class PuppyListViewModel
@Inject
constructor(
    private val repository: PuppyRepository,
    @Named("key") private val key: String,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val puppies: MutableState<List<Puppy>> = mutableStateOf(ArrayList())

    val loading = mutableStateOf(false)

    // Pagination starts at '1' (-1 = exhausted)
    val lastId = mutableStateOf(-1)

    var puppyListScrollPosition = 0

    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }

        onTriggerEvent(PuppyListEvent.NewSearchEvent)
    }

    fun onTriggerEvent(event: PuppyListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is PuppyListEvent.NewSearchEvent -> {
                        newSearch()
                    }
                    is PuppyListEvent.NextPageEvent -> {
                        nextPage()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: $e, ${e.cause}")
                e.printStackTrace()
            } finally {
                Log.d(TAG, "launchJob: finally called.")
            }
        }
    }

    private fun newSearch() {
        // New search. Reset the state
        resetSearchState()

        repository.loadMore(key = key, -1).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                puppies.value = list
                lastId.value = dataState.lastId ?: -1
            }

            dataState.error?.let { error ->
                Log.e(TAG, "newSearch: $error")
            }
        }.launchIn(viewModelScope)
    }

    private fun nextPage() {
        repository.loadMore(key = key, lastId = lastId.value).onEach { dataState ->
            loading.value = dataState.loading

            dataState.data?.let { list ->
                appendPuppies(list)
                lastId.value = dataState.lastId ?: -1
            }

            dataState.error?.let { error ->
                Log.e(TAG, "nextPage: $error")
            }
        }.launchIn(viewModelScope)
    }

    /**
     * Append new puppies to the current list of puppies
     */
    private fun appendPuppies(puppies: List<Puppy>) {
        val current = ArrayList(this.puppies.value)
        current.addAll(puppies)
        this.puppies.value = current
    }

    private fun incrementPage() {
        setPage(lastId.value + 1)
    }

    fun onChangePuppyScrollPosition(position: Int) {
        setListScrollPosition(position = position)
    }

    /**
     * Called when a new search is executed.
     */
    private fun resetSearchState() {
        puppies.value = listOf()
        lastId.value = 1
        onChangePuppyScrollPosition(0)
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    private fun setListScrollPosition(position: Int) {
        puppyListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int) {
        this.lastId.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setQuery(query: String) {
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }
}
