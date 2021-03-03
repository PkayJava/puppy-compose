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

import com.example.androiddevchallenge20212021.network.PuppyService
import com.example.androiddevchallenge20212021.network.model.PuppyDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePuppyMapper(): PuppyDtoMapper {
        return PuppyDtoMapper()
    }

    @Singleton
    @Provides
    fun providePuppyService(): PuppyService {
        return Retrofit.Builder()
            .baseUrl("https://api.mockaroo.com/api/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(PuppyService::class.java)
    }

    @Singleton
    @Provides
    @Named("key")
    fun provideKey(): String {
        return "50b0d8c0"
    }
}
