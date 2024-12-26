package kz.dev.sneakers_shop.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.fragment.NavHostFragment
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.data.RoomShoesProvider
import kz.dev.sneakers_shop.data.room.entities.Cart
import kz.dev.sneakers_shop.presentation.catalog.Catalog
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
                }
            )

           // Log.e("dd", "db: ${viewModel.getShoes()}")
        }
    }

}

