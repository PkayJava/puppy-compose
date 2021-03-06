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
package com.example.androiddevchallenge20212021.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androiddevchallenge20212021.cache.PuppyDao
import com.example.androiddevchallenge20212021.cache.model.PuppyEntity

@Database(entities = [PuppyEntity::class ], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun puppyDao(): PuppyDao

    companion object {
        val DATABASE_NAME: String = "puppy_db"
    }
}
