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

import android.app.Application
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import java.io.File

@HiltAndroidApp
class MainApplication : Application() {

    fun createDataStore(
        name: String,
        corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
        migrations: List<DataMigration<Preferences>> = listOf(),
        scope: CoroutineScope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    ): DataStore<Preferences> =
        PreferenceDataStoreFactory.create(
            corruptionHandler = corruptionHandler,
            migrations = migrations,
            scope = scope
        ) {
            File(this.filesDir, "datastore/$name.preferences_pb")
        }
}
