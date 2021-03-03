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
package com.example.androiddevchallenge20212021.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androiddevchallenge20212021.cache.model.PuppyEntity
import com.example.androiddevchallenge20212021.util.PAGINATION_PAGE_SIZE

@Dao
interface PuppyDao {

    @Insert
    suspend fun insertPuppy(puppy: PuppyEntity): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPuppies(puppies: List<PuppyEntity>): LongArray

    @Query("SELECT * FROM puppies WHERE id = :id")
    suspend fun getPuppyById(id: Int): PuppyEntity?

    @Query(
        """
        SELECT * FROM puppies  
        ORDER BY id DESC LIMIT :pageSize OFFSET ((:page - 1) * :pageSize)
        """
    )
    suspend fun searchPuppies(
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE
    ): List<PuppyEntity>

    @Query(
        """
        SELECT * FROM puppies
        WHERE id > :lastId
        ORDER BY id ASC LIMIT 30
    """
    )
    suspend fun getAllPuppies(lastId: Int): List<PuppyEntity>

    @Query(
        """
        SELECT * FROM puppies 
        ORDER BY id ASC LIMIT (:page * :pageSize)
        """
    )
    suspend fun restorePuppies(
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE
    ): List<PuppyEntity>

    @Query(
        """
        SELECT * FROM puppies 
        ORDER BY id DESC LIMIT (:page * :pageSize)
    """
    )
    suspend fun restoreAllPuppies(
        page: Int,
        pageSize: Int = PAGINATION_PAGE_SIZE
    ): List<PuppyEntity>
}
