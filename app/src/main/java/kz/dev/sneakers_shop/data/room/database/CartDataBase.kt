package kz.dev.sneakers_shop.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kz.dev.sneakers_shop.data.room.dao.CartDao
import kz.dev.sneakers_shop.data.room.entities.Cart


@Database(entities = [Cart::class], version = 1)
abstract class CartDataBase: RoomDatabase() {
    abstract fun getItemsCart(): CartDao
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