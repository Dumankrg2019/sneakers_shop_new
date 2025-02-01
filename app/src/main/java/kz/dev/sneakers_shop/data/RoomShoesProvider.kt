package kz.dev.sneakers_shop.data

import android.content.Context
import kz.dev.sneakers_shop.data.room.database.DatabaseHolder
import kz.dev.sneakers_shop.data.room.entities.Cart
import kz.dev.sneakers_shop.data.room.entities.Users

class RoomShoesProvider(
    context: Context
): RoomFunctions {

    private val dao = DatabaseHolder.getOrCreate(context.applicationContext).getItemsCart()
    private val userDao = DatabaseHolder.getOrCreate(context.applicationContext).getItemUser()

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


    fun getUser(login: String): Users {
        return userDao.getUser(login)
    }

     fun checkUserCredentials(login: String, password: String): String {
        val user = getUser(login)
        return when {
            user == null -> "User does not exist"
            user.password != password -> "Password is not correct"
            else -> "Login successful"
        }
    }

    fun addUser(user: Users) {
        userDao.addUser(user)
    }
}