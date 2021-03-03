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
package com.example.androiddevchallenge20212021.util

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.androiddevchallenge20212021.R
import kotlinx.coroutines.ExperimentalCoroutinesApi

const val DEFAULT_PUPPY_IMAGE = R.drawable.puppy_blank

@ExperimentalCoroutinesApi
@Composable
fun loadPicture(url: String, @DrawableRes defaultImage: Int): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // show default image while image loads
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(defaultImage)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .fallback(R.drawable.dummy_image)
        .load(url)
        .into(object : CustomTarget<Bitmap>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}

@ExperimentalCoroutinesApi
@Composable
fun loadPicture(drawableId: Int): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(drawableId)
        .into(object : CustomTarget<Bitmap>() {

            override fun onLoadCleared(placeholder: Drawable?) {}

            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}

@ExperimentalCoroutinesApi
@Composable
fun loadPuppy(puppyFile: String): MutableState<Bitmap?> {

    val bitmapState: MutableState<Bitmap?> = mutableStateOf(null)

    var resourceId: Int = when (puppyFile) {
        "p1.jpg" -> R.drawable.p1
        "p2.jpg" -> R.drawable.p2
        "p3.jpg" -> R.drawable.p3
        "p4.jpg" -> R.drawable.p4
        "p5.jpg" -> R.drawable.p5
        "p6.jpg" -> R.drawable.p6
        "p7.jpg" -> R.drawable.p7
        "p8.jpg" -> R.drawable.p8
        "p9.jpg" -> R.drawable.p9
        "p10.jpg" -> R.drawable.p10
        "p11.jpg" -> R.drawable.p11
        "p12.jpg" -> R.drawable.p12
        "p13.jpg" -> R.drawable.p13
        "p14.jpg" -> R.drawable.p14
        "p15.jpg" -> R.drawable.p15
        "p16.jpg" -> R.drawable.p16
        "p17.jpg" -> R.drawable.p17
        "p18.jpg" -> R.drawable.p18
        "p19.jpg" -> R.drawable.p19
        "p20.jpg" -> R.drawable.p20
        "p21.jpg" -> R.drawable.p21
        "p22.jpg" -> R.drawable.p22
        else -> R.drawable.p22
    }

    // get network image
    Glide.with(LocalContext.current)
        .asBitmap()
        .load(resourceId)
        .into(object : CustomTarget<Bitmap>() {

            override fun onLoadCleared(placeholder: Drawable?) {}

            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap>?
            ) {
                bitmapState.value = resource
            }
        })

    return bitmapState
}
