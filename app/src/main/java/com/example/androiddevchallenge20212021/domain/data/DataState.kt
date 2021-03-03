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
package com.example.androiddevchallenge20212021.domain.data

data class DataState<out T>(
    val data: T? = null,
    val error: String? = null,
    val loading: Boolean = false,
    val lastId: Int? = null,
) {

    companion object {

        fun <T> success(data: T, lastId: Int?): DataState<T> {
            return DataState(
                data = data,
                lastId = lastId
            )
        }

        fun <T> success(data: T): DataState<T> {
            return DataState(
                data = data
            )
        }

        fun <T> error(message: String): DataState<T> {
            return DataState(
                error = message
            )
        }

        fun <T> loading(): DataState<T> {
            return DataState(loading = true)
        }
    }
}
