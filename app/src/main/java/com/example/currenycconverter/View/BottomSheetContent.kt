package com.example.currenycconverter.View

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.currenycconverter.domain.model.ExchangeRate

@Composable
fun BottomSheetContent(
    onItemClicked: (String) -> Unit,
    currenciesList: List<ExchangeRate>
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        items(currenciesList) { currency ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onItemClicked(currency.code) }
                    .padding(vertical = 4.dp, horizontal = 16.dp)
            ) {
                Text(text = "${currency.code}: ${currency.name}")
            }
        }
    }
}