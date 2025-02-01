package kz.dev.sneakers_shop.presentation.auth

data class AuthScreenState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)