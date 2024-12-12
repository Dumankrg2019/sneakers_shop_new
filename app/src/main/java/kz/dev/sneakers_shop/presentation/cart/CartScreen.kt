package kz.dev.sneakers_shop.presentation.cart

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.ui.theme.GreyBackground
import kz.dev.sneakers_shop.ui.theme.GreyText

@Composable
fun CartScreen() {

    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .background(color = GreyBackground)
            .padding(vertical = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Header()
        WhenEmptyCart()
//        Spacer(modifier = Modifier.height(26.dp))
//        ItemOfCart()
//        Spacer(modifier = Modifier.height(16.dp))
//        TotalPriceBlock()
//        Spacer(modifier = Modifier.weight(1f))
//        ButtonConfirm()
    }
}

@Composable
private fun  Header() {
    Text(
        text = stringResource(R.string.cart),
        style = TextStyle(
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight(700),
            lineHeight = 22.sp
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun ItemOfCart() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(0.dp)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(0.dp))
                .background(Color.White),
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shoes_brown_orange),
                    contentDescription = "shoes",
                     contentScale = ContentScale.Fit,
                    alignment = Alignment.Center
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "New Balance",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight(600),
                            lineHeight = 18.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Кроссовки 993 Brown из коллаборации с Aimé Leon Dore",
                        style = TextStyle(
                            color = GreyText,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(400),
                            lineHeight = 16.sp
                        ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = "$179",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight(600),
                            lineHeight = 18.sp
                        ),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .height(36.dp)
                        .clip(RoundedCornerShape(100.dp))
                        .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_minus),
                                contentDescription = "minus",
                                tint = Color.White
                                )
                            Spacer(modifier = Modifier.width(24.dp))
                            Text(
                                text = "18",
                                style = TextStyle(
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight(400),
                                    lineHeight = 20.sp
                                ),
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.width(24.dp))
                            Icon(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = "minus",
                                tint = Color.White
                            )

                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TotalPriceBlock() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "4 items: Total (Including Delivery) ",
            style = TextStyle(
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight(400),
                lineHeight = 18.sp
            ),
        )
        Text(
            text = "$1232",
            style = TextStyle(
                color = Color.Black,
                fontSize = 13.sp,
                fontWeight = FontWeight(600),
                lineHeight = 18.sp
            ),
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun ButtonConfirm() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(54.dp),
        onClick = {

        },
        shape = RoundedCornerShape(32.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        ),
    ) {
        Text(
            text = stringResource(R.string.confirm_order),
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

@Composable
private fun WhenEmptyCart() {
    Box(modifier = Modifier.fillMaxSize()) {
        Icon(
            painter = painterResource(id = R.drawable.bg_empty_basket),
            contentDescription = "abstraction")
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.cart_is_empty),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 28.sp,
                    fontWeight = FontWeight(600),
                    lineHeight = 34.sp,
                ),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.find_interesting_models_in_the_catalog),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight(400),
                    lineHeight = 22.sp,
                ),
                textAlign = TextAlign.Center
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    CartScreen()

}