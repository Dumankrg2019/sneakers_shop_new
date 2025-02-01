package kz.dev.sneakers_shop.presentation.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.fragment.NavHostFragment
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.data.RoomShoesProvider
import kz.dev.sneakers_shop.presentation.HomeViewModel
import kz.dev.sneakers_shop.util.viewModels
import kotlin.math.log


class RegistrationFragment : Fragment() {

    private val viewModel: AuthViewModel by viewModels(
        viewModelInitializer = {
            AuthViewModel(roomProvider = RoomShoesProvider(requireContext()))
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )= ComposeView(requireContext()).apply {
        val navController = NavHostFragment.findNavController(this@RegistrationFragment)
        setContent {
            RegistrationScreen(
                authIn = {
                navController.navigate(R.id.action_registrationFragment_to_authFragment)
            },
                addUser = {login, password ->
                    viewModel.addUser(login, password)
                }
            )
        }
    }

}