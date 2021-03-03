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

import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun DefaultSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit?,
) {
    SnackbarHost(
        hostState = snackbarHostState,
        snackbar = { data ->
            Snackbar(
                modifier = Modifier.padding(16.dp),
                content = {
                    Text(
                        text = data.message,
                        style = MaterialTheme.typography.body2,
                        color = Color.White
                    )
                },
                action = {
                    data.actionLabel?.let { actionLabel ->
                        TextButton(
                            onClick = {
                                onDismiss()
                            }
                        ) {
                            Text(
                                text = actionLabel,
                                style = MaterialTheme.typography.body2,
                                color = Color.White
                            )
                        }
                    }
                }
            )
        },
        modifier = modifier
    )
}
