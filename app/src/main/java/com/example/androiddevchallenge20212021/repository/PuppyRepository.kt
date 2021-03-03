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
package com.example.androiddevchallenge20212021.repository

import com.example.androiddevchallenge20212021.cache.PuppyDao
import com.example.androiddevchallenge20212021.cache.model.PuppyEntityMapper
import com.example.androiddevchallenge20212021.domain.data.DataState
import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.network.PuppyService
import com.example.androiddevchallenge20212021.network.model.PuppyDtoMapper
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PuppyRepository(
    private val dao: PuppyDao,
    private val entityMapper: PuppyEntityMapper,
    private val dtoMapper: PuppyDtoMapper,
    private val puppyService: PuppyService,
) {

    fun loadMore(
        key: String,
        lastId: Int,
    ): Flow<DataState<List<Puppy>>> = flow {
        try {
            emit(DataState.loading())

            delay(1000)

            var cacheResult = dao.getAllPuppies(lastId = lastId)
            if (cacheResult.isEmpty()) {
                val puppies = getPuppyFromNetwork(key = key)
                dao.insertPuppies(entityMapper.toEntityList(puppies))
            }
            cacheResult = dao.getAllPuppies(lastId = lastId)
            val list = entityMapper.fromEntityList(cacheResult)
            emit(DataState.success(data = list, lastId = list[list.size - 1].id))
        } catch (e: Exception) {
            emit(DataState.error<List<Puppy>>(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getPuppyFromNetwork(key: String): List<Puppy> {
        return dtoMapper.toDomainList(puppyService.list(key = key, count = 100))
    }

    fun loadDetail(
        puppyId: Int
    ): Flow<DataState<Puppy>> = flow {
        try {
            emit(DataState.loading())

            delay(1000)

            var puppy = getPuppyFromCache(puppyId = puppyId)

            if (puppy != null) {
                emit(DataState.success(puppy))
            } else {
                puppy = getPuppyFromCache(puppyId = puppyId)
                if (puppy != null) {
                    emit(DataState.success(puppy))
                } else {
                    throw Exception("Unable to get puppy from the cache.")
                }
            }
        } catch (e: Exception) {
            emit(DataState.error<Puppy>(e.message ?: "Unknown Error"))
        }
    }

    private suspend fun getPuppyFromCache(puppyId: Int): Puppy? {
        return dao.getPuppyById(puppyId)?.let { puppyEntity ->
            entityMapper.mapToDomainModel(puppyEntity)
        }
    }
}
