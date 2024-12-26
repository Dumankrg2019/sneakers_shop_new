package kz.dev.sneakers_shop.presentation.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.NavHostFragment
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.data.RoomShoesProvider
import kz.dev.sneakers_shop.data.room.entities.Cart
import kz.dev.sneakers_shop.presentation.HomeViewModel
import kz.dev.sneakers_shop.presentation.catalog.Catalog
import kz.dev.sneakers_shop.util.viewModels


class CartFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels(
        viewModelInitializer = {
            HomeViewModel(roomProvider = RoomShoesProvider(requireContext()))
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {

        setContent {
            CartScreen(viewModel.getShoes(), { id ->
                viewModel.deleteById(id)
            }, {id->
                viewModel.decrementCount(id)
            }, {id->
                viewModel.incrementCount(id)
            }, {viewModel.getShoes()})
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getShoes()
    }

}