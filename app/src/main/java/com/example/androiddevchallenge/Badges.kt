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

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.blueDark
import com.example.androiddevchallenge.ui.theme.blueLigh
import com.example.androiddevchallenge.ui.theme.redDark
import com.example.androiddevchallenge.ui.theme.redLigh
import com.example.androiddevchallenge.ui.theme.shapes
import com.example.androiddevchallenge.ui.theme.yellowDark
import com.example.androiddevchallenge.ui.theme.yellowLigh

data class Badge(val text: String, val colorLight: Color, val colorDark: Color)

enum class Badges(val badge: Badge) {
    NEW(Badge("New", colorLight = redLigh, colorDark = redDark)),
    TERRIER(Badge("Terrier", colorLight = yellowLigh, colorDark = yellowDark)),
    LABRADOR(Badge("Labrador", colorLight = blueLigh, colorDark = blueDark))
}

@Composable
fun Badge(data: Badge) {
    val textColor = if (isSystemInDarkTheme()) data.colorDark else data.colorLight
    val backgroundColor = textColor.copy(alpha = 0.4f)

    Box(
        modifier = Modifier
            .clip(shapes.small)
            .background(color = backgroundColor)
    ) {
        Text(
            data.text,
            color = textColor,
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

@Composable
fun BadgeList(values: List<Badge>) {
    LazyRow(modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp, bottom = 8.dp)) {
        items(items = values) { item ->
            Badge(item)
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}

@Preview("Badge", widthDp = 180, heightDp = 320)
@Composable
fun BadgePreview() {
    MyTheme {
        Badge(Badges.TERRIER.badge)
    }
}

@Preview("List Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun BadgeListLightPreview() {
    MyTheme {
        BadgeList(listOf(Badges.TERRIER.badge, Badges.NEW.badge, Badges.LABRADOR.badge))
    }
}

@Preview("List Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun BadgeListDarkPreview() {
    MyTheme(darkTheme = true) {
        BadgeList(listOf(Badges.TERRIER.badge, Badges.NEW.badge, Badges.LABRADOR.badge))
    }
}
