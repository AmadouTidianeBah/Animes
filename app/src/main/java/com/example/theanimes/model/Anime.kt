package com.example.theanimes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Anime(
    @StringRes val name: Int,
    @DrawableRes val img: Int,
    @StringRes val description: Int
)
