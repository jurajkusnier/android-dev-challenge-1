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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.pageHorizontalPadding

@Composable
fun Header() {
    Column(
        modifier = Modifier.padding(
            top = 80.dp,
            bottom = 16.dp,
            start = pageHorizontalPadding,
            end = pageHorizontalPadding
        )
    ) {
        Text(
            "Find Your",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onSurface
        )
        Text(
            "Lovely pet in anywhere",
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.75f)
        )
    }
}

@Preview("Header Light")
@Composable
fun HeaderPreviewLight() {
    MyTheme {
        Box(modifier = Modifier.background(MaterialTheme.colors.surface)) { Header() }
    }
}

@Preview("Header Dark")
@Composable
fun HeaderPreviewDark() {
    MyTheme(darkTheme = true) {
        Box(modifier = Modifier.background(MaterialTheme.colors.surface)) { Header() }
    }
}
