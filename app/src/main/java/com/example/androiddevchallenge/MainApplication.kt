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
package com.example.androiddevchallenge

import android.app.Application
import android.content.res.Resources
import kotlin.math.roundToInt

class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private lateinit var instance: MainApplication

        fun pxToDp(value: Int): Int {
            return kotlin.math.ceil(value.toDouble() / instance.resources.displayMetrics.density)
                .roundToInt()
        }

        fun getScreenWidth(): Int {
            return Resources.getSystem().displayMetrics.widthPixels
        }
    }
}
