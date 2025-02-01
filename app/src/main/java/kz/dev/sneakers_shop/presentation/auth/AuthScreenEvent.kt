package kz.dev.sneakers_shop.presentation.auth

sealed class AuthScreenEvent {
    data class EnterEmail(val email: String): AuthScreenEvent()
    data class EnterPassword(val password: String): AuthScreenEvent()
    object LoginClicked: AuthScreenEvent()
    object RegistrationClicked: AuthScreenEvent()
    object ForgotPasswordClicked : AuthScreenEvent()
}