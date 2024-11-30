package kz.dev.sneakers_shop.presentation.catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.data.Sneakers
import kz.dev.sneakers_shop.ui.theme.GreyBackground
import kz.dev.sneakers_shop.ui.theme.GreyText

@Composable
fun Catalog() {
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize()
            .background(color = GreyBackground)
            .padding(top = 10.dp, bottom = 12.dp)
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        HeaderCatalog()
        Spacer(modifier = Modifier.height(20.dp))
        ListSneakers()
    }
}

@Composable
private fun  HeaderCatalog() {
    Text(
        text = stringResource(R.string.hello_sneakerhead),
        style = TextStyle(
            color = Color.Black,
            fontSize = 17.sp,
            fontWeight = FontWeight(600),
            lineHeight = 22.sp,
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun SneakersCard(listShoes: Sneakers) {
    var isBasket by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(285.dp)
            .clickable {  },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box  (
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(4.dp))
                .background(Color.White),


            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
                    .padding(bottom = 12.dp)
            ) {
                Image(
                    painter = painterResource(id = listShoes.img),
                    contentScale = ContentScale.Fit,
                    alignment = Alignment.Center,
                    contentDescription = "shoes img")
                Text(
                    text = listShoes.title,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 13.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 18.sp
                    ),
                )
                Text(
                    text = listShoes.shortDescr,
                    style = TextStyle(
                        color = GreyText,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400),
                        lineHeight = 16.sp
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "$${listShoes.price}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(600),
                        lineHeight = 18.sp
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(36.dp)
                        .alpha(if(isBasket) 0.7f else 1f),
                    onClick = {
                        isBasket = !isBasket
                    },
                    shape = RoundedCornerShape(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),

                ) {
                    Text(
                        text = if(isBasket) stringResource(R.string.remove) else stringResource(R.string.add_to_card),
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight(400),
                            lineHeight = 20.sp,
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}

@Composable
private fun ListSneakers() {
    val listOfSneakers = listOf(
        Sneakers(
            "Dolce & Gabbana",
            R.drawable.shoes_dolce,
            "Кеды с принтом граффити",
            1251.0
        ),
        Sneakers(
            "Off-White-Pink",
            R.drawable.shoes_pink,
            "Кроссовки Off-Court-Pink 3.0",
            551.0
        ),
        Sneakers(
            "Anime shoes",
            R.drawable.shoes_white,
            "Анимэшная обувь",
            396.0
        ),
        Sneakers(
            "Arrow fashion",
            R.drawable.shoes_white_arrow,
            "Кроссовки Off-Court 3.0",
            741.0
        ),
        Sneakers(
            "Jordan",
            R.drawable.shoes_jordan,
            "Кеды с принтом граффити",
            1654.0
        ),
        Sneakers(
            "Jordan Brown",
            R.drawable.shoes_brown_jordan,
            "Баскетбольные Jordan",
            1830.0
        ),
        Sneakers(
            "New Balance",
            R.drawable.shoes_gray,
            "Кроссовки 993 Brown из коллаборации с Aimé Leon Dore",
            1120.0
        ),
        Sneakers(
            "Martini shoes",
            R.drawable.shoes_brown_orange,
            "Лучшая коллекция Martini shoes",
            2530.0
        ),
        Sneakers(
            "Standart black shoes",
            R.drawable.shoes_black,
            "Comfotable and usefull for everyday",
            400.0
        ),
    )
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 10.dp)
    ) {
        items(listOfSneakers) {item->
            SneakersCard(item)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun Preview() {
    Catalog()
}