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
package com.example.androiddevchallenge20212021.di

import com.example.androiddevchallenge20212021.cache.PuppyDao
import com.example.androiddevchallenge20212021.cache.model.PuppyEntityMapper
import com.example.androiddevchallenge20212021.network.PuppyService
import com.example.androiddevchallenge20212021.network.model.PuppyDtoMapper
import com.example.androiddevchallenge20212021.repository.PuppyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @ViewModelScoped
    @Provides
    fun providePuppyRepository(
        dao: PuppyDao,
        entityMapper: PuppyEntityMapper,
        dtoMapper: PuppyDtoMapper,
        puppyService: PuppyService,
    ): PuppyRepository {
        return PuppyRepository(
            dao = dao,
            entityMapper = entityMapper,
            dtoMapper = dtoMapper,
            puppyService = puppyService,
        )
    }
}
