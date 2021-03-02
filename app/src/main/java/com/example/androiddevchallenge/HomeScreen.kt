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

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.repository.Pett
import com.example.androiddevchallenge.repository.PettsRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pageHorizontalPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

sealed class HomeScreenItems {
    object Header : HomeScreenItems()
    object Category : HomeScreenItems()
    object Search : HomeScreenItems()
    data class Title(val text: String) : HomeScreenItems()
    data class PetsRow(val first: Pett, val second: Pett) : HomeScreenItems()
}

@Composable
fun HomeScreen(pettsRepository: PettsRepository, navHostController: NavController? = null) {

    fun openDetails(pett: Pett) {
        navHostController?.navigate(Screen.getDetailPath(pett.id))
    }

    val pageItems = mutableListOf(
        HomeScreenItems.Header,
        HomeScreenItems.Search,
        HomeScreenItems.Title("Pet Category"),
        HomeScreenItems.Category,
        HomeScreenItems.Title("Pets")
    )

    pettsRepository.petts.chunked(2).forEach { (first, second) ->
        pageItems.add(HomeScreenItems.PetsRow(first, second))
    }
    val listState = rememberLazyListState()
    val alpha: Float by animateFloatAsState(if (listState.firstVisibleItemIndex == 0 && listState.firstVisibleItemScrollOffset < 25f) 0f else 1f)

    LazyColumn(modifier = Modifier.statusBarsPadding(), state = listState) {
        items(items = pageItems) { item ->
            when (item) {
                HomeScreenItems.Header -> Header()
                HomeScreenItems.Category -> CategorySelectors()
                is HomeScreenItems.PetsRow -> {
                    Row(
                        modifier = Modifier.padding(
                            start = pageHorizontalPadding,
                            end = pageHorizontalPadding,
                            bottom = 16.dp
                        )
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            ListItem(
                                item = item.first,
                                isLiked = pettsRepository.isLiked(item.first),
                                onLike = {
                                    pettsRepository.toggleLike(it)
                                }
                            ) {
                                openDetails(it)
                            }
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            ListItem(
                                item = item.second,
                                isLiked = pettsRepository.isLiked(item.second),
                                onLike = {
                                    pettsRepository.toggleLike(it)
                                }
                            ) {
                                openDetails(it)
                            }
                        }
                    }
                }
                is HomeScreenItems.Title -> {
                    Text(
                        text = item.text,
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f),
                        modifier = Modifier.padding(
                            start = pageHorizontalPadding,
                            end = pageHorizontalPadding,
                            top = 16.dp,
                            bottom = 8.dp
                        )
                    )
                }
                HomeScreenItems.Search -> SearchBarPlaceholder()
            }
        }
    }

    MyTopAppBar(
        home = true,
        alpha = alpha,
        title = stringResource(id = R.string.app_name),
        goUp = { }
    )
}

@Preview("Light Theme", widthDp = 720, heightDp = 1980)
@Composable
fun HomeScreenLightPreview() {
    MyTheme {
        HomeScreen(PettsRepository())
    }
}

@Preview("Dark Theme", widthDp = 720, heightDp = 1980)
@Composable
fun HomeScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        HomeScreen(PettsRepository())
    }
}
