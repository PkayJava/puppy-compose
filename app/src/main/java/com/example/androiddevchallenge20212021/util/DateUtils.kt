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
package com.example.androiddevchallenge20212021.util

import java.text.SimpleDateFormat
import java.util.Date

object DateUtils {

    private val sdf = SimpleDateFormat("yyyy-MM-dd")

    private val sdf1 = SimpleDateFormat("dd MMM yyyy")

    fun longToDate(long: Long): Date {
        return Date(long)
    }

    fun dateToLong(date: Date): Long {
        return date.time / 1000 // return seconds
    }

    // Ex: November 4, 2021
    fun dateToString(date: Date): String {
        return sdf.format(date)
    }

    fun dateToString1(date: Date): String {
        return sdf1.format(date)
    }

    fun stringToDate(string: String): Date {
        return sdf.parse(string)
            ?: throw NullPointerException("Could not convert date string to Date object.")
    }

    fun createTimestamp(): Date {
        return Date()
    }
}
