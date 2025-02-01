package kz.dev.sneakers_shop.presentation.registration

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.data.RoomShoesProvider
import kz.dev.sneakers_shop.data.room.entities.Users
import kz.dev.sneakers_shop.presentation.auth.AuthScreenEvent
import kz.dev.sneakers_shop.presentation.auth.AuthScreenState
import kotlin.math.log

class AuthViewModel(
    private val roomProvider: RoomShoesProvider,
): ViewModel() {

    private val _state = mutableStateOf(AuthScreenState())
    val state: State<AuthScreenState> = _state

    fun getUser(login: String): Users {
        return roomProvider.getUser(login)
    }

    fun checkCredentials(login: String, password: String): String {
        return roomProvider.checkUserCredentials(login, password)
    }

    fun addUser(login: String, password: String) {
        roomProvider.addUser(Users(login = login, password = password))
    }

    fun handleEvent(event: AuthScreenEvent, navController: NavController) {
        when(event) {
            is AuthScreenEvent.EnterEmail -> {
                _state.value = _state.value.copy(email = event.email)
            }
            is AuthScreenEvent.EnterPassword -> {
                _state.value = _state.value.copy(password = event.password)
            }
            is AuthScreenEvent.LoginClicked -> {
                navController.navigate(R.id.action_authFragment_to_catalogFragment)
            }
            is AuthScreenEvent.RegistrationClicked -> {
                navController.navigate(R.id.action_authFragment_to_registrationFragment)
            }
            is AuthScreenEvent.ForgotPasswordClicked -> {
                Log.e("dd", "not exist")
            }

        }
    }
}