package kz.dev.sneakers_shop.data

import android.content.Context
import kz.dev.sneakers_shop.data.room.database.DatabaseHolder
import kz.dev.sneakers_shop.data.room.entities.Cart

class RoomShoesProvider(
    context: Context
): RoomFunctions {

    private val dao = DatabaseHolder.getOrCreate(context.applicationContext).getItemsCart()

    private fun Cart.toCart(): Cart {
        return Cart(
            id =  id,
            countOfProduct = countOfProduct + 1
        )
    }

    override fun setShoesOnDb(cart: Cart) {
        dao.addItemOfCart(cart)
    }

    fun createShoes(cart: Cart) {
        dao.addItemOfCart(cart)
    }

    fun getShoes(): List<Cart> {
        return  dao.getAllItemsFromCart()
    }

    override fun getShoesOnBd(): List<Cart> {
      return  dao.getAllItemsFromCart()
    }

    override fun deleteByItem(id: Int) {
        dao.deleteItemById(id)
    }

    fun incrementCount(id: Int) {
        dao.incrementCount(id)
    }

    fun decrementCount(id: Int) {
        dao.decrementCount(id)
    }
}