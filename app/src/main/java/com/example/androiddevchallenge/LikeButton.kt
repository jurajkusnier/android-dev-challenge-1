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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.redLigh

enum class LikeButtonSize(val value: Dp) {
    SMALL(32.dp),
    BIG(44.dp)
}

@Composable
fun LikeButton(
    like: Boolean,
    size: LikeButtonSize,
    onClick: () -> Unit = {}
) {
    Button(
        elevation = ButtonDefaults.elevation(defaultElevation = 4.dp, pressedElevation = 8.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(2.dp),
        colors = buttonColors(backgroundColor = if (like) redLigh else Color.White),
        modifier = Modifier
            .width(size.value)
            .height(size.value),
        onClick = onClick
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
            colorFilter = ColorFilter.tint(color = if (like) Color.White else Color.LightGray),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .padding(6.dp)
        )
    }
}

@Preview("Like Light")
@Composable
fun LikePreviewLight() {
    MyTheme {
        LikeButton(true, LikeButtonSize.SMALL)
    }
}

@Preview("Like Dark")
@Composable
fun LikePreviewDark() {
    MyTheme {
        LikeButton(false, LikeButtonSize.BIG)
    }
}
