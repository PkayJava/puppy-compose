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

import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.domain.util.DomainMapper

class PuppyDtoMapper : DomainMapper<PuppyDto, Puppy> {

    override fun mapToDomainModel(model: PuppyDto): Puppy {
        return Puppy(
            id = model.id,
            name = model.name,
            avatar = model.avatar,
            dateOfBirth = model.dateOfBirth,
            description = model.description,
            gender = model.gender,
            phrase = model.phrase,
            puppyFile = model.puppyFile,
        )
    }

    override fun mapFromDomainModel(domainModel: Puppy): PuppyDto {
        return PuppyDto(
            id = domainModel.id,
            name = domainModel.name,
            avatar = domainModel.avatar,
            dateOfBirth = domainModel.dateOfBirth,
            description = domainModel.description,
            gender = domainModel.gender,
            phrase = domainModel.phrase,
            puppyFile = domainModel.puppyFile,
        )
    }

    fun toDomainList(initial: List<PuppyDto>): List<Puppy> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Puppy>): List<PuppyDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}
