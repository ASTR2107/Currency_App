package com.example.currenycconverter.View

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.currenycc.ScreenEvent

/*
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoScreen(
    state: ScreenState,
    onEvent: (ScreenEvent) -> Unit
) {
    val number = remember { mutableStateOf("") }

    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        if (state.error != null) {
            Toast.makeText(context,state.error, Toast.LENGTH_LONG).show()
        }

    }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState()
    var shouldBottomSheetShow by remember {
        mutableStateOf(false)
    }

    if (shouldBottomSheetShow) {
        ModalBottomSheet(
            onDismissRequest = { shouldBottomSheetShow = false}
        ) {

        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 160.dp)
    ) {

        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = "Currensy converter",
            fontFamily = FontFamily.Serif,
            fontSize = 23.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.padding(top = 25.dp))

        Box(
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        CurrencyInfo(
                            modifier = Modifier.fillMaxWidth(),
                            textCurrency = state.currencyCode,
                            courseCurrency = state.exchangeRates[state.currencyCode]?.name ?: "",
                            onClick = {}
                        )
                        TextField(
                            number.value,
                            { number.value = it },
                            textStyle = TextStyle(fontSize = 28.sp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        CurrencyInfo(
                            textCurrency = state.toCurrencyCode,
                            courseCurrency = state.exchangeRates[state.toCurrencyCode]?.name ?: "",
                            onClick = {}

                        )
                    }
                    TextField(
                        number.value,
                        { number.value = it },
                        textStyle = TextStyle(fontSize = 28.sp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }
            }
        }
        Box(modifier = Modifier
            .padding(start = 37.dp)
            .clip(CircleShape)
            .clickable { onEvent(ScreenEvent.SwapIconClicked) }
            .background(color = MaterialTheme.colorScheme.background)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_swap_vert_24),
                contentDescription = "jgasfj",
                modifier = Modifier
                    .size(17.dp)
                    .padding(50.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
    LazyVerticalGrid(modifier = Modifier.padding(horizontal = 35.dp),
        columns = GridCells.Fixed(3)
    ) {

    }


}

@Composable
fun KeyboardButton(modifier: Modifier, key: String, backgroundColor: Color, onClick: () -> Unit) {

}


@Composable
fun CurrencyInfo(
    modifier: Modifier = Modifier,
    textCurrency: String,
    courseCurrency: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .height(60.dp)
                .padding(start = 15.dp)
        ) {
            Text(text = textCurrency)
            SmallFloatingActionButton(
                onClick = { onClick() },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.ArrowDropDown, "Small floating action button.")
            }
        }
        Text(text = courseCurrency)


    }

}


@Composable
fun SliderAdvancedExample() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 3,
            valueRange = 0f..50f
        )
        Text(text = sliderPosition.toString())
    }
}

 */

