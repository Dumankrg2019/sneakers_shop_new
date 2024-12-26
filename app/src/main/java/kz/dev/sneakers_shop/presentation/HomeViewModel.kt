package kz.dev.sneakers_shop.presentation

import androidx.lifecycle.ViewModel
import kz.dev.sneakers_shop.data.RoomShoesProvider
import kz.dev.sneakers_shop.data.room.entities.Cart

class HomeViewModel(
    private val roomProvider: RoomShoesProvider,
): ViewModel() {

    fun createShoes(cart: Cart) {
        roomProvider.createShoes(cart)
    }

    fun getShoes(): List<Cart> {
        return roomProvider.getShoes()
    }

    fun deleteById(id: Int) {
        roomProvider.deleteByItem(id)
    }

    fun decrementCount(id: Int) {
        roomProvider.decrementCount(id)
    }

    fun incrementCount(id: Int) {
        roomProvider.incrementCount(id)
    }
}