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

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Composable
fun MyTopAppBar(
    home: Boolean,
    title: String? = null,
    alpha: Float = 0f,
    goUp: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = {
            Text(
                if (alpha > 0f) title ?: "" else "",
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.75f)
            )
        },
        backgroundColor = MaterialTheme.colors.surface.copy(alpha = alpha),
        elevation = (alpha * 4).dp,
        actions = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.75f)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = goUp, modifier = Modifier.padding(start = 6.dp)) {
                Icon(
                    painter = painterResource(
                        id = if (home)
                            R.drawable.ic_pets_24px
                        else
                            R.drawable.ic_baseline_arrow_back_24
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface.copy(alpha = 0.75f)
                )
            }
        }
    )
}

@Preview("App Bar - Light", widthDp = 360, heightDp = 60)
@Composable
fun MyTopAppBarPreview() {
    MyTheme {
        MyTopAppBar(home = true) {}
    }
}

@Preview("App Bar - Dark", widthDp = 360, heightDp = 60)
@Composable
fun MyTopAppBarPreviewDark() {
    MyTheme(darkTheme = true) {
        MyTopAppBar(home = false, "Pet Name") {}
    }
}
