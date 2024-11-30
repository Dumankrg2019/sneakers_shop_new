package kz.dev.sneakers_shop.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.NavHostFragment


class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ComposeView(requireContext()).apply {
        val navController = NavHostFragment.findNavController(this@AuthFragment)
        setContent {
            Authentication {
                navController.navigate(
                    AuthFragmentDirections.actionAuthFragmentToCatalogFragment()
                )
            }
        }
    }
}

