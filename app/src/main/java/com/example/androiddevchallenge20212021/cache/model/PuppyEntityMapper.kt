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

import com.example.androiddevchallenge20212021.domain.model.Puppy
import com.example.androiddevchallenge20212021.domain.util.DomainMapper

class PuppyEntityMapper : DomainMapper<PuppyEntity, Puppy> {

    override fun mapToDomainModel(model: PuppyEntity): Puppy {
        return Puppy(
            id = model.id,
            name = model.name,
            description = model.description,
            avatar = model.avatar,
            dateOfBirth = model.dateOfBirth,
            gender = model.gender,
            phrase = model.phrase,
            puppyFile = model.puppyFile,
        )
    }

    override fun mapFromDomainModel(domainModel: Puppy): PuppyEntity {
        return PuppyEntity(
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

    /**
     * "Carrot, potato, Chicken, ..."
     */
    private fun convertIngredientListToString(ingredients: List<String>): String {
        val ingredientsString = StringBuilder()
        for (ingredient in ingredients) {
            ingredientsString.append("$ingredient,")
        }
        return ingredientsString.toString()
    }

    private fun convertIngredientsToList(ingredientsString: String?): List<String> {
        val list: ArrayList<String> = ArrayList()
        ingredientsString?.let {
            for (ingredient in it.split(",")) {
                list.add(ingredient)
            }
        }
        return list
    }

    fun fromEntityList(initial: List<PuppyEntity>): List<Puppy> {
        return initial.map { mapToDomainModel(it) }
    }

    fun toEntityList(initial: List<Puppy>): List<PuppyEntity> {
        return initial.map { mapFromDomainModel(it) }
    }
}
