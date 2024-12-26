package kz.dev.sneakers_shop.data

import kz.dev.sneakers_shop.data.room.entities.Cart

interface RoomFunctions {
    fun setShoesOnDb(cart: Cart)

    fun getShoesOnBd(): List<Cart>

    fun deleteByItem(id: Int)
}

