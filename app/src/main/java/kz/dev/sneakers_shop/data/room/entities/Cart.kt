package kz.dev.sneakers_shop.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Cart")
data class Cart(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "count_of_product") val countOfProduct: Int = 1
)
