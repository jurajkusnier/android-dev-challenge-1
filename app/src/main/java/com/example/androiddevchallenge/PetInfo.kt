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

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.repository.Pett
import com.example.androiddevchallenge.repository.PettsRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.blueButton
import com.example.androiddevchallenge.ui.theme.borderColorDark
import com.example.androiddevchallenge.ui.theme.borderColorLight
import com.example.androiddevchallenge.ui.theme.pageHorizontalPadding
import com.example.androiddevchallenge.ui.theme.shapes

@Composable
fun PettDetailsBox(title: String, subtitle: String) {
    val boldSubtitle = MaterialTheme
        .typography
        .subtitle2
        .copy(fontWeight = FontWeight.Bold)
    val smallSubtitle = MaterialTheme
        .typography
        .subtitle2
        .copy(fontSize = 12.sp)

    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) borderColorDark else borderColorLight,
                shape = shapes.medium
            )
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                style = boldSubtitle,
                color = MaterialTheme.colors.onSurface
            )
            Text(
                subtitle,
                style = smallSubtitle,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
            )
        }
    }
}

@Composable
fun BottomBar() {
    Column(
        Modifier.fillMaxHeight(),
        Arrangement.Bottom
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0x00000000),
                            Color(0x10000000),
                        )
                    )
                )
        )

        Column(Modifier.background(MaterialTheme.colors.surface)) {
            Box(
                modifier = Modifier
                    .padding(vertical = 16.dp, horizontal = pageHorizontalPadding)
            ) {
                Button(
                    shape = shapes.large,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = blueButton),
                ) {
                    Text("Adopt Me!", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun PetInfo(
    pett: Pett,
    isLiked: Boolean,
    listState: LazyListState = rememberLazyListState(),
    onLike: (Pett) -> Unit
) {
    LazyColumn(state = listState) {
        item {
            Box {
                Image(
                    painter = painterResource(id = pett.largeImage),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    contentScale = ContentScale.FillHeight
                )
            }

            Box(
                modifier = Modifier
                    .padding(pageHorizontalPadding)
                    .fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = pett.name,
                        style = MaterialTheme.typography.h1,
                    )
                    Text(
                        text = pett.description,
                        style = MaterialTheme.typography.h3,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                    )
                }
                Box(modifier = Modifier.align(Alignment.CenterEnd)) {
                    LikeButton(
                        like = isLiked,
                        size = LikeButtonSize.BIG,
                        onClick = { onLike(pett) }
                    )
                }
            }
            Row(modifier = Modifier.padding(pageHorizontalPadding)) {
                Box(modifier = Modifier.weight(1f)) {
                    PettDetailsBox(title = "4 months", subtitle = "Age")
                }
                Spacer(Modifier.size(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    PettDetailsBox(title = "Grey", subtitle = "Color")
                }
                Spacer(Modifier.size(16.dp))
                Box(modifier = Modifier.weight(1f)) {
                    PettDetailsBox(title = "11 kg", subtitle = "Weight")
                }
            }

            Text(
                "Pet story",
                style = MaterialTheme.typography.h1,
                modifier = Modifier.padding(pageHorizontalPadding)
            )
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas condimentum neque at quam viverra, et faucibus quam porta. Vivamus feugiat pharetra nibh, sit amet imperdiet risus condimentum vitae. Nam aliquet nibh in lorem ullamcorper, gravida suscipit velit consectetur. Maecenas porttitor convallis consectetur.",
                modifier = Modifier.padding(horizontal = pageHorizontalPadding),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas condimentum neque at quam viverra, et faucibus quam porta. Vivamus feugiat pharetra nibh, sit amet imperdiet risus condimentum vitae. Nam aliquet nibh in lorem ullamcorper, gravida suscipit velit consectetur. Maecenas porttitor convallis consectetur.",
                modifier = Modifier.padding(
                    start = pageHorizontalPadding,
                    end = pageHorizontalPadding,
                    top = 16.dp
                ),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
            Text(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas condimentum neque at quam viverra, et faucibus quam porta. Vivamus feugiat pharetra nibh, sit amet imperdiet risus condimentum vitae. Nam aliquet nibh in lorem ullamcorper, gravida suscipit velit consectetur. Maecenas porttitor convallis consectetur.",
                modifier = Modifier.padding(
                    start = pageHorizontalPadding,
                    end = pageHorizontalPadding,
                    top = 16.dp
                ),
                textAlign = TextAlign.Justify,
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
            )
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
}

@Preview("Bottom Bar")
@Composable
fun BottomBarPreview() {
    MyTheme {
        Surface {
            BottomBar()
        }
    }
}

@Preview("Light Theme", widthDp = 720, heightDp = 1280)
@Composable
fun PetInfoLightPreview() {
    MyTheme {
        Surface {
            PetInfo(PettsRepository().petts.first(), true) {}
        }
    }
}

@Preview("Dark Theme", widthDp = 720, heightDp = 1280)
@Composable
fun PetInfoDarkPreview() {
    MyTheme(darkTheme = true) {
        Surface {
            PetInfo(PettsRepository().petts.first(), false) {}
        }
    }
}
