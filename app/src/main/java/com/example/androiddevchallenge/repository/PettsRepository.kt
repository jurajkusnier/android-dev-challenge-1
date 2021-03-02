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
package com.example.androiddevchallenge.repository

import com.example.androiddevchallenge.Badges
import com.example.androiddevchallenge.R

class PettsRepository {

    private val likes = mutableSetOf("3")

    fun isLiked(pett: Pett) = likes.contains(pett.id)

    fun toggleLike(pett: Pett): Boolean {
        return if (isLiked(pett)) {
            likes.remove(pett.id)
            false
        } else {
            likes.add(pett.id)
            true
        }
    }

    val petts = listOf(
        Pett(
            id = "1",
            smallImage = R.drawable.dog_1_small,
            largeImage = R.drawable.dog_1,
            name = "Charlie",
            description = "Male • 1 year old",
            badges = listOf(Badges.LABRADOR.badge)
        ),
        Pett(
            id = "2",
            smallImage = R.drawable.dog_2_small,
            largeImage = R.drawable.dog_2,
            name = "Max",
            description = "Female • 6 months old",
            badges = listOf(Badges.NEW.badge, Badges.TERRIER.badge)
        ),
        Pett(
            id = "3",
            smallImage = R.drawable.dog_3_small,
            largeImage = R.drawable.dog_3,
            name = "Buddy",
            description = "Male • 1 year old",
            badges = listOf(Badges.NEW.badge, Badges.LABRADOR.badge)
        ),
        Pett(
            id = "4",
            smallImage = R.drawable.dog_4_small,
            largeImage = R.drawable.dog_4,
            name = "Oscar",
            description = "Female • 6 months old",
            badges = listOf(Badges.TERRIER.badge)
        ),
        Pett(
            id = "5",
            smallImage = R.drawable.dog_5_small,
            largeImage = R.drawable.dog_5,
            name = "Milo",
            description = "Female • 3 months old",
            badges = listOf(Badges.NEW.badge)
        ),
        Pett(
            id = "6",
            smallImage = R.drawable.dog_6_small,
            largeImage = R.drawable.dog_6,
            name = "Archie",
            description = "Female • 2 year old",
            badges = listOf(Badges.LABRADOR.badge)
        )
    )
}
