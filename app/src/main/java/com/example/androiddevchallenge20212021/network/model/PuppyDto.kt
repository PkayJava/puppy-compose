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
package com.example.androiddevchallenge20212021.network.model

import com.google.gson.annotations.SerializedName

data class PuppyDto(

    @SerializedName("id")
    var id: Int?,

    @SerializedName("name")
    var name: String,

    @SerializedName("description")
    var description: String,

    @SerializedName("avatar")
    var avatar: String,

    @SerializedName("date_of_birth")
    var dateOfBirth: String,

    @SerializedName("gender")
    var gender: String,

    @SerializedName("phrase")
    var phrase: String,

    @SerializedName("puppy_file")
    var puppyFile: String,

)
