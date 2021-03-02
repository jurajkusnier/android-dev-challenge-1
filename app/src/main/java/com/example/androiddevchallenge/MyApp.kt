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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.repository.PettsRepository
import com.example.androiddevchallenge.ui.theme.MyTheme

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details?id=$ID_PLACEHOLDER")

    companion object {
        private const val ID_PLACEHOLDER = "{id}"
        fun getDetailPath(id: String) = Details.route.replace(ID_PLACEHOLDER, id)
    }
}

@ExperimentalAnimationApi
@Composable
fun MyApp(pettsRepository: PettsRepository) {
    val navController = rememberNavController()

    Surface(color = MaterialTheme.colors.background) {
        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                HomeScreen(pettsRepository, navController)
            }
            composable(
                Screen.Details.route,
                arguments = listOf(
                    navArgument("id") { defaultValue = "" }
                )
            ) {
                val argument = it.arguments?.getString("id") ?: ""
                DetailScreen(pettsRepository, argument, navController)
            }
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun AppLightPreview() {
    MyTheme {
        MyApp(PettsRepository())
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun AppDarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp(PettsRepository())
    }
}
