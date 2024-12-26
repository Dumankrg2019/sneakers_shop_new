package kz.dev.sneakers_shop.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.dev.sneakers_shop.data.room.entities.Cart

@Dao
interface CartDao {

    @Query("SELECT *FROM  Cart")
        fun getAllItemsFromCart(): List<Cart>

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addItemOfCart(cart: Cart)

    @Delete
    fun deleteItem(cart: Cart)

    @Query("DELETE FROM Cart WHERE id = :id")
    fun deleteItemById(id: Int)


    @Query("UPDATE Cart SET count_of_product = count_of_product + 1 WHERE id = :id")
     fun incrementCount(id: Int)

    @Query("UPDATE Cart SET count_of_product = count_of_product - 1 WHERE id = :id")
     fun decrementCount(id: Int)
}