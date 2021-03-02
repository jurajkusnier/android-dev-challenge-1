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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.pageHorizontalPadding
import com.example.androiddevchallenge.ui.theme.shapes

@Composable
fun SearchBarPlaceholder() {
    Card(
        shape = shapes.large,
        backgroundColor = MaterialTheme.colors.onSurface.copy(alpha = 0.08f),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = pageHorizontalPadding)
            .height(48.dp),
        elevation = 0.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {},
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_search_24),
                colorFilter = ColorFilter.tint(color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)),
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp, start = 16.dp)
            )
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Search",
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}
