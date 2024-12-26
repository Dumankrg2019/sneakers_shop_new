package kz.dev.sneakers_shop.presentation.cart

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kz.dev.sneakers_shop.R
import kz.dev.sneakers_shop.data.Shoes.listOfSneakers
import kz.dev.sneakers_shop.data.Sneakers
import kz.dev.sneakers_shop.data.room.entities.Cart
import kz.dev.sneakers_shop.ui.theme.GreyBackground
import kz.dev.sneakers_shop.ui.theme.GreyText


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CartScreen(listSneakersInDb: List<Cart>? = null,
               onRemoveItemInDb:(id: Int)-> Unit,
               onDecrementItem:(id: Int)-> Unit,
               onIncrementItem:(id: Int)-> Unit,
               onGetCurrentListInDB:() -> List<Cart>?) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = GreyBackground)
        .safeContentPadding(),
        contentAlignment = Alignment.Center) {
        // Scrollable content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 15.dp),
//            verticalArrangement = Arrangement.Center
        ) {
            Header()
            Spacer(modifier = Modifier.height(26.dp))

            val dbList = onGetCurrentListInDB()?.toMutableList()

            val dbListState = remember {
                mutableStateOf<List<Cart>?>(onGetCurrentListInDB())
            }

            Log.e("dd", "db state: ${dbListState}")
            val sneakers = remember {
                mutableStateListOf<Sneakers>().apply {
                    addAll(listOfSneakers.filter { sneaker ->
                        dbList?.any { cart -> cart.id == sneaker.id } ?: true
                    }.map{sneaker ->
                        val item = dbList?.find { cart -> cart.id == sneaker.id }
                        sneaker.copy(count = item?.countOfProduct ?: 1)
                    }
                    )
                }
            }



            Log.e("dd", "show current list shoes inDB: ${sneakers.toList()}")
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(bottom = 80.dp)
            ) {
                items(items = sneakers , key = {it.title}) {item->
                    SwipeToDismissItem(
                        item = { ItemOfCart(item, onRemoveItemInDb, onDecrementItem,
                            onIncrementItem, sneakers, dbListState) },
                        onRemove = {
                            onRemoveItemInDb(item.id)
                            sneakers.remove(item)
                                   },
                        modifier = Modifier.animateItemPlacement(tween(100)))
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp)) // Adjust height if necessary
                    TotalPriceBlock()
                }
            }
        }
        // Bottom components
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            ButtonConfirm()
        }
    }
    }


@Composable
private fun  Header() {
    Text(
        modifier = Modifier.fillMaxWidth(),
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
private fun ItemOfCart(
    sneakers: Sneakers,
    onRemoveItemInDb:(id: Int)-> Unit,
    onDecrementItem:(id: Int)-> Unit,
    onIncrementItem:(id: Int)-> Unit,
    sneakersState: SnapshotStateList<Sneakers>?,
    bdState: MutableState<List<Cart>?>?
) {
    val sneakersFromDbItem =  bdState?.value?.filter { it.id == sneakers.id }?.get(0)
    val isFirstLoading = remember {
        mutableStateOf(true)
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(Color.White),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(0.dp)
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
                    painter = painterResource(id = sneakers.img),
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
                        text = sneakers.title,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 13.sp,
                            fontWeight = FontWeight(600),
                            lineHeight = 18.sp
                        ),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = sneakers.shortDescr,
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
                                modifier = Modifier.clickable {
                                    isFirstLoading.value = false
                                    if(sneakers.count == 1) {
                                        onRemoveItemInDb(sneakers.id)
                                        sneakersState?.remove(sneakers)

                                    } else {
                                        onDecrementItem(sneakers.id)
                                        sneakersState?.let {
                                            val index = it.indexOfFirst { item -> item.id == sneakers.id }
                                            if (index != -1) {
                                                it[index] = it[index].copy(count = sneakers.count - 1)
                                            }
                                            sneakersFromDbItem?.copy(countOfProduct = - 1)
                                        }
                                    }
                                },
                                painter = painterResource(id = R.drawable.ic_minus),
                                contentDescription = "minus",
                                tint = Color.White
                                )
                            Spacer(modifier = Modifier.width(24.dp))
                            Text(
                                text = "${if(isFirstLoading.value){sneakersFromDbItem?.countOfProduct} else{sneakers.count} }",
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
                                modifier = Modifier.clickable {
                                    onIncrementItem(sneakers.id)
                                    isFirstLoading.value = false
                                    sneakersState?.let {
                                        val index = it.indexOfFirst { item -> item.id == sneakers.id }
                                        if (index != -1) {
                                            it[index] = it[index].copy(count = sneakers.count + 1)
                                        }
                                    }
                                    sneakersFromDbItem?.copy(countOfProduct = + 1)
                                    bdState?.value?.toMutableList()?.let {
                                        val index = it.indexOfFirst { item->  item.id == sneakers.id }
                                        if (index != -1) {
                                            it.let { shoes->
                                                shoes[index] = shoes[index].copy(countOfProduct = sneakers.count +1)
                                            }

                                        }
                                    }
                                },
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
            painter = painterResource(id = R.drawable.bg_abstraction),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissItem(
    item: @Composable ()-> Unit,
    onRemove: () -> Unit,
    modifier: Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val swipeToDismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {state->
            if(state == SwipeToDismissBoxValue.EndToStart) {
                coroutineScope.launch {
                    delay(800)
                    onRemove()
                }
                true
            } else {
                false
            }
        }
    )
    SwipeToDismissBox(
        state = swipeToDismissState,
        backgroundContent = {
            val backgroundColor by animateColorAsState(
                targetValue = when(swipeToDismissState.currentValue) {
                    SwipeToDismissBoxValue.StartToEnd -> Color.Green
                    SwipeToDismissBoxValue.EndToStart -> Color.Red
                    SwipeToDismissBoxValue.Settled ->   Color.Red
                },
            )
            // Calculate alpha based on swipe progress
            val progress = if (swipeToDismissState.targetValue == SwipeToDismissBoxValue.EndToStart) {
                swipeToDismissState.progress
            } else {
                0f
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(backgroundColor),
                contentAlignment = Alignment.CenterEnd // Align to the end (right side)
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(end = 24.dp)
                        .graphicsLayer(alpha = 1f) // Fade in as user swipes
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Delete",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 17.sp,
                            fontWeight = FontWeight(400),
                            lineHeight = 22.sp
                        )
                    )
                }
            }
    },
        modifier = modifier) {
        item()

    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CartScreen(onRemoveItemInDb = {}, onDecrementItem = {}, onIncrementItem = {}, onGetCurrentListInDB = { emptyList() })

}