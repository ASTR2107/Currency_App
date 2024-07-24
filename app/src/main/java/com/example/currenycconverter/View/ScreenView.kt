package com.example.currenycconverter.View

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currencyconverter.R
import com.example.currenycc.ScreenEvent
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableInteractionSource")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun ScreenView(
    state: ScreenState,
    onEvent: (ScreenEvent) -> Unit
) {

    val keys = listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "0", "C")

    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
        }
    }

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var shouldBottomSheetShow by remember { mutableStateOf(false) }

    if (shouldBottomSheetShow) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { shouldBottomSheetShow = false },
            dragHandle = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    BottomSheetDefaults.DragHandle()
                    Text(
                        text = "Select Currency",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            },
            content = {
                BottomSheetContent(
                    onItemClicked = { currencyCode ->
                        onEvent(ScreenEvent.BottomSheetClickItem(currencyCode))
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) shouldBottomSheetShow = false
                        }
                    },
                    currenciesList = state.exchangeRates.values.toList()
                )
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "CurrencyApp",
            fontFamily = FontFamily.Monospace,
            fontSize = 40.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        CurrencyInfo(
                            modifier = Modifier.fillMaxWidth(),
                            textCurrency = state.currencyCode,
                            courseCurrency = state.exchangeRates[state.currencyCode]?.name ?: "",
                            onDropDownIconClicked = {
                                shouldBottomSheetShow = true
                                onEvent(ScreenEvent.FromCurrencySelect)
                            }
                        )
                        Text(
                            text = state.currencyCode,
                            fontSize = 40.sp,
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = { onEvent(ScreenEvent.FromCurrencySelect) }
                            ),
                            color = if (state.selection == SelectionState.FROM) {
                                MaterialTheme.colorScheme.primary
                            } else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = state.toCurrencyValue,
                            fontSize = 40.sp,
                            modifier = Modifier.clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClick = { onEvent(ScreenEvent.ToCurrencySelect) }
                            ),
                            color = if (state.selection == SelectionState.TO) {
                                MaterialTheme.colorScheme.primary
                            } else MaterialTheme.colorScheme.onSurface
                        )
                        CurrencyInfo(
                            modifier = Modifier.fillMaxWidth(),
                            textCurrency = state.toCurrencyCode,
                            courseCurrency = state.exchangeRates[state.toCurrencyCode]?.name ?: "",
                            onDropDownIconClicked = {
                                shouldBottomSheetShow = true
                                onEvent(ScreenEvent.ToCurrencySelect)
                            }
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .padding(start = 40.dp)
                    .clip(CircleShape)
                    .clickable { onEvent(ScreenEvent.SwapIconClicked) }
                    .background(color = MaterialTheme.colorScheme.background)
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_swap_vert_24),
                    contentDescription = "Swap Currency",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(25.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        }
        LazyVerticalGrid(
            modifier = Modifier.padding(horizontal = 35.dp),
            columns = GridCells.Fixed(3)
        ) {
            items(keys) { key ->
                KeyboardButton(
                    modifier = Modifier.aspectRatio(1f),
                    key = key,
                    backgroundColor = if (key == "C") MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.surfaceVariant,
                    onClick = {
                        onEvent(ScreenEvent.ButtonClickNumber(key))
                    }
                )
            }
        }
    }
}


@Composable
fun CurrencyInfo(
    modifier: Modifier = Modifier,
    textCurrency: String,
    courseCurrency: String,
    onDropDownIconClicked: () -> Unit
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = textCurrency, fontSize = 14.sp, fontWeight = FontWeight.Bold)
        IconButton(onClick = onDropDownIconClicked) {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Open Bottom Sheet"
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = courseCurrency, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun KeyboardButton(
    modifier: Modifier = Modifier,
    key: String,
    backgroundColor: androidx.compose.ui.graphics.Color,
    onClick: (String) -> Unit
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .clip(CircleShape)
            .background(color = androidx.compose.ui.graphics.Color.Blue)
            .clickable { onClick(key) },
        contentAlignment = Alignment.Center
    ) {
        Text(text = key, fontSize = 32.sp)
    }
}

