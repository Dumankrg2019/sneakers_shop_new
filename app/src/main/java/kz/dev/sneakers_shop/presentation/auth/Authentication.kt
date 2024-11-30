package kz.dev.sneakers_shop.presentation.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.ui.theme.GreyBackground
import kz.dev.sneakers_shop.ui.theme.GreyText

@Composable
fun Authentication(signIn: () -> Unit) {

    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .background(color = Color.White)
            .padding(top = 10.dp, bottom = 24.dp)
            .padding(horizontal = 16.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header()
        Spacer(modifier = Modifier.height(52.dp))
        LoginField()
        Spacer(modifier = Modifier.height(16.dp))
        PasswordField()
        Spacer(modifier = Modifier.weight(1f))
        ButtonSignIn(signIn)

    }
}

@Composable
fun Header() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "arrow left"
        )
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(R.string.welcome_back),
            style = TextStyle(
                color = Color.Black,
                fontSize = 17.sp,
                fontWeight = FontWeight(700),
                lineHeight = 22.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LoginField() {
    var userName by remember {
        mutableStateOf("")
    }
    var focusState by remember {
        mutableStateOf(false)
    }
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .onFocusChanged { focus ->
                focusState = focus.isFocused
            }
            .border(
                width = if (focusState) 2.dp else 0.dp,
                color = if (focusState) Color.Black else Color.Transparent,
                shape = if (focusState) RoundedCornerShape(4.dp) else RoundedCornerShape(0.dp)
            ),

        colors = TextFieldDefaults.textFieldColors(
            containerColor = GreyBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            lineHeight = 24.sp,
        ),
        value = userName,
        onValueChange = {
            userName = it
        },
        placeholder = {
            Text(
                text = "Username",
                style = TextStyle(
                    color = GreyText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 21.sp,
                ),
                overflow = TextOverflow.Ellipsis
            )
        },
        maxLines = 1,

        )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PasswordField() {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordFocus by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .onFocusChanged { focus ->
                passwordFocus = focus.isFocused
            }
            .border(
                width = if (passwordFocus) 2.dp else 0.dp,
                color = if (passwordFocus) Color.Black else Color.Transparent,
                shape = if (passwordFocus) RoundedCornerShape(4.dp) else RoundedCornerShape(0.dp)
            ),
        placeholder = {
            Text(
                text = stringResource(R.string.password),
                style = TextStyle(
                    color = GreyText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 21.sp,
                ),
                overflow = TextOverflow.Ellipsis
            )
        },
        maxLines = 1,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            if(passwordFocus && password.isNotEmpty()) {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password"
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = GreyBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight(600),
            lineHeight = 24.sp,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ButtonSignIn(signIn: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp),
        onClick = {
            signIn()
        },
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        ),
    ) {
        Text(
            text = stringResource(R.string.sign_in),
            style = TextStyle(
                color = Color.White,
                fontSize = 17.sp,
                fontWeight = FontWeight(600),
                lineHeight = 22.sp,
            ),
            textAlign = TextAlign.Center
        )
    }
}