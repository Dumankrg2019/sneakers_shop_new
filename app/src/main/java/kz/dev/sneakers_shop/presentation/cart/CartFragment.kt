package kz.dev.sneakers_shop.presentation.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.presentation.catalog.Catalog


class CartFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        setContent {
            Catalog()
        }
    }

}