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
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.androiddevchallenge.repository.PettsRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.LocalWindowInsets

@ExperimentalAnimationApi
@Composable
fun DetailScreen(
    pettsRepository: PettsRepository,
    id: String,
    navHostController: NavController? = null
) {
    val listState = rememberLazyListState()
    val alpha: Float by animateFloatAsState(if (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset < MainApplication.getScreenWidth() * 0.7f) 0f else 1f)
    val pett = pettsRepository.petts.first { it.id == id }
    val isLiked = remember { mutableStateOf(pettsRepository.isLiked(pett)) }
    Surface {
        val insetHeight = MainApplication.pxToDp(LocalWindowInsets.current.statusBars.top)
        PetInfo(pett, isLiked.value, listState) { isLiked.value = pettsRepository.toggleLike(it) }
        Box(
            Modifier
                .background(
                    MaterialTheme.colors.surface.copy(
                        alpha = alpha
                    )
                )
                .fillMaxWidth()
                .height(insetHeight.dp)
        )
        MyTopAppBar(
            home = false,
            alpha = alpha,
            title = pett.name,
            goUp = { navHostController?.popBackStack() }
        )

        BottomBar()
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 720, heightDp = 1980)
@Composable
fun DetailScreenLightPreview() {
    MyTheme {
        DetailScreen(PettsRepository(), "1")
    }
}

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 720, heightDp = 1980)
@Composable
fun DetailScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        DetailScreen(PettsRepository(), "1")
    }
}
