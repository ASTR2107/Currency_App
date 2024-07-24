package com.example.currenycconverter.View

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currenycc.ScreenEvent
import kotlinx.coroutines.launch
/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyConverterScreen(
    state: ScreenState,
    onEvent: (ScreenEvent) -> Unit
) {
    var amount by remember { mutableStateOf("") }
    var fromCurrency by remember { mutableStateOf("USD") }
    var toCurrency by remember { mutableStateOf("EUR") }
    var convertedAmount by remember { mutableStateOf("0.0") }
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
                    Divider()
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
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Конвертация валют",
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Сумма") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Здесь можно использовать DropDownMenu для выбора валют
        CurrencyInfo(
            modifier = Modifier.fillMaxWidth(),
            textCurrency = state.toCurrencyCode,
            courseCurrency = state.exchangeRates[state.toCurrencyCode]?.name ?: "",
            onDropDownIconClicked = {
                shouldBottomSheetShow = true
                onEvent(ScreenEvent.ToCurrencySelect)
            }
        )
        Text(text = "Из валюты: $fromCurrency")
        // Здесь вы можете добавить Dropdown или Radio Button для выбора валюты

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "В валюту: $toCurrency")
        // Здесь вы можете добавить Dropdown или Radio Button для выбора валюты
        CurrencyInfo(
            modifier = Modifier.fillMaxWidth(),
            textCurrency = state.toCurrencyCode,
            courseCurrency = state.exchangeRates[state.toCurrencyCode]?.name ?: "",
            onDropDownIconClicked = {
                shouldBottomSheetShow = true
                onEvent(ScreenEvent.ToCurrencySelect)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            // Логика конвертации валют
            // Например, вы можете использовать API для получения курсов валют и расчета
            convertedAmount = performCurrencyConversion(amount, fromCurrency, toCurrency)
        }) {
            Text(text = "Конвертировать")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Конвертированная сумма: $convertedAmount",
        )
    }
}

fun performCurrencyConversion(amount: String, from: String, to: String): String {
    // Здесь реализуйте логику для конвертации валют
    return "0.0" // Пример, замените на реальную логику
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

 */

