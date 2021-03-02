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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.repository.Pett
import com.example.androiddevchallenge.repository.PettsRepository
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.borderColorDark
import com.example.androiddevchallenge.ui.theme.borderColorLight
import com.example.androiddevchallenge.ui.theme.shapes

@Composable
fun ListItem(
    item: Pett,
    isLiked: Boolean = false,
    onLike: (item: Pett) -> Unit = {},
    onClick: (item: Pett) -> Unit = {}
) {
    val tmpLike = remember { mutableStateOf(isLiked) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) borderColorDark else borderColorLight,
                shape = shapes.medium
            ),
        shape = shapes.medium,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier.clickable { onClick(item) }
        ) {
            Image(
                painter = painterResource(id = item.smallImage),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.25f)
                    .padding(top = 0.dp),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            BadgeList(item.badges)
            Text(
                text = item.name,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = item.description,
                style = MaterialTheme.typography.subtitle1.copy(
                    color = MaterialTheme.colors.onSurface.copy(
                        alpha = 0.40f
                    )
                ),
                modifier = Modifier.padding(
                    start = 8.dp,
                    bottom = 16.dp,
                    end = 8.dp
                )
            )
        }
        Row(horizontalArrangement = Arrangement.End, modifier = Modifier.padding(8.dp)) {
            LikeButton(
                like = tmpLike.value,
                size = LikeButtonSize.SMALL,
                onClick = { onLike(item); tmpLike.value = !tmpLike.value }
            )
        }
    }
}

@Preview("Pet", widthDp = 180, heightDp = 320)
@Composable
fun PerCardPreview() {
    MyTheme {
        ListItem(item = PettsRepository().petts.first())
    }
}
