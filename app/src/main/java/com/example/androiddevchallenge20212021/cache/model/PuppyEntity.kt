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
package com.example.androiddevchallenge20212021.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "puppies")
data class PuppyEntity(

    // Value from API
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "avatar")
    var avatar: String,

    @ColumnInfo(name = "date_of_birth")
    var dateOfBirth: String,

    @ColumnInfo(name = "gender")
    var gender: String,

    @ColumnInfo(name = "phrase")
    var phrase: String,

    @ColumnInfo(name = "puppy_file")
    var puppyFile: String,

)
