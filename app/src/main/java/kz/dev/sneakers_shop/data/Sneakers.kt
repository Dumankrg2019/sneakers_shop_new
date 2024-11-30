package kz.dev.sneakers_shop.data

import androidx.annotation.DrawableRes

data class Sneakers(
    val title: String,
    @DrawableRes
    val img: Int,
    val shortDescr: String,
    val price: Double
)
