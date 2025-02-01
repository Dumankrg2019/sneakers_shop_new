package kz.dev.sneakers_shop.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class Users(
    @PrimaryKey val id: Int = 0,
    @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "password") val password: String
)
