package kz.dev.sneakers_shop.presentation.catalog

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.NavHostFragment
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.data.RoomShoesProvider
import kz.dev.sneakers_shop.data.room.entities.Cart
import kz.dev.sneakers_shop.presentation.HomeViewModel
import kz.dev.sneakers_shop.util.viewModels


class CatalogFragment : Fragment() {


    private val viewModel: HomeViewModel by viewModels(
        viewModelInitializer = {
            HomeViewModel(roomProvider = RoomShoesProvider(requireContext()))
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        val navController = NavHostFragment.findNavController(this@CatalogFragment)
        setContent {
            Catalog(
                clickOnShoes = {
                    navController.navigate(R.id.action_catalogFragment_to_cartFragment)
                },
                addShoesOnDb = { id->
                    viewModel.createShoes(Cart(id = id, countOfProduct = 1))
                },
                onRemoveItem = {id->
                    viewModel.deleteById(id)
                }
            )

           // Log.e("dd", "db: ${viewModel.getShoes()}")
        }
    }

}

