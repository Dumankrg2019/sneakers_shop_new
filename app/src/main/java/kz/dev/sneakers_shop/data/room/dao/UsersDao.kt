package kz.dev.sneakers_shop.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.dev.sneakers_shop.data.room.entities.Cart
import kz.dev.sneakers_shop.data.room.entities.Users

@Dao
interface UsersDao {

    @Query("SELECT *FROM  Users WHERE login = :login")
    fun getUser(login: String): Users

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: Users)
}