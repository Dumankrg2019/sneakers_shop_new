package kz.dev.sneakers_shop.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.dev.sneakers_shop.data.room.dao.CartDao
import kz.dev.sneakers_shop.data.room.dao.UsersDao
import kz.dev.sneakers_shop.data.room.entities.Cart
import kz.dev.sneakers_shop.data.room.entities.Users


@Database(entities = [Cart::class, Users::class], version = 2)
abstract class CartDataBase: RoomDatabase() {
    abstract fun getItemsCart(): CartDao
    abstract fun getItemUser(): UsersDao
}

object DatabaseHolder {

    private var _database: CartDataBase? = null

    val database: CartDataBase get() = _database!!

    fun getOrCreate(context: Context): CartDataBase {
        if (_database == null) {
            _database = Room.databaseBuilder(
                context,
                CartDataBase::class.java,
                "cart_database",
            )
                .allowMainThreadQueries()
                .build()
        }

        return database
    }
}