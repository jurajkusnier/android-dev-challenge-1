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

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.elevation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.borderColorDark
import com.example.androiddevchallenge.ui.theme.borderColorLight
import com.example.androiddevchallenge.ui.theme.pageHorizontalPadding
import com.example.androiddevchallenge.ui.theme.shapes

@Preview("Category", widthDp = 360, heightDp = 640)
@Composable
fun CategoryPreview() {
    MyTheme {
        CategorySelectors()
    }
}

data class Category(val title: String, val itemsCount: Int, @DrawableRes val icon: Int)

@Composable
fun CategorySelectors() {
    val items = listOf(
        Category("Dogs", 42, R.drawable.avatar_dog_transparent),
        Category("Cats", 83, R.drawable.avatar_cat_transparent),
        Category("Bunnies", 12, R.drawable.avatar_turtle_transparent),
        Category("Hamsters", 18, R.drawable.avatar_hamster_transparent),
    )

    Column(modifier = Modifier.padding(horizontal = pageHorizontalPadding)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                CategorySelector(
                    title = items[0].title,
                    subtitle = "Total of ${items[0].itemsCount}",
                    icon = items[0].icon,
                    color = Color(0xFFF3B051)
                ) {}
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                CategorySelector(
                    title = items[1].title,
                    subtitle = "Total of ${items[1].itemsCount}",
                    icon = items[1].icon,
                    color = Color(0xFF64C7FA)
                ) {}
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                CategorySelector(
                    title = items[2].title,
                    subtitle = "Total of ${items[2].itemsCount}",
                    icon = items[2].icon,
                    color = Color(0xFF479847)
                ) {}
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                CategorySelector(
                    title = items[3].title,
                    subtitle = "Total of ${items[3].itemsCount}",
                    icon = items[3].icon,
                    color = Color(0xFFEE8362)
                ) {}
            }
        }
    }
}

@Composable
fun CategorySelector(
    title: String,
    subtitle: String,
    @DrawableRes icon: Int,
    color: Color,
    onClick: () -> Unit
) {

    val boldSubtitle = MaterialTheme
        .typography
        .subtitle2
        .copy(fontWeight = FontWeight.Bold)
    val smallSubtitle = MaterialTheme
        .typography
        .subtitle2
        .copy(fontSize = 12.sp)

    Button(
        elevation = elevation(defaultElevation = 0.dp, pressedElevation = 4.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.surface),
        shape = shapes.medium,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .border(
                width = 1.dp,
                color = if (isSystemInDarkTheme()) borderColorDark else borderColorLight,
                shape = shapes.medium
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .fillMaxHeight()
                    .aspectRatio(1f)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = color.copy(alpha = 0.2f))
                        .fillMaxSize()
                )
                Image(
                    painter = painterResource(id = icon),
                    modifier = Modifier
                        .padding(4.dp)
                        .align(Alignment.Center),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(color = color)

                )
            }
            Column(modifier = Modifier.padding(start = 8.dp)) {
                Text(title, style = boldSubtitle, color = MaterialTheme.colors.onSurface)
                Text(
                    subtitle,
                    style = smallSubtitle,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                )
            }
        }
    }
}
