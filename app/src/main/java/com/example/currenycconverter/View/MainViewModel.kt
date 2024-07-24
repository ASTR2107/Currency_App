package com.example.currenycconverter.View

import android.icu.text.DecimalFormat
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currenycc.ScreenEvent
import com.example.currenycconverter.domain.model.Resourse
import com.example.currenycconverter.domain.repository.CurrencyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MainViewModel @Inject constructor (private val repository: CurrencyRepository) : ViewModel() {
    var state by mutableStateOf(ScreenState())

    init {
        getCurrencyList()
    }

    fun onEvent(event: ScreenEvent) {
        when(event) {
            ScreenEvent.FromCurrencySelect -> {
                state = state.copy(selection = SelectionState.FROM)
            }

            ScreenEvent.ToCurrencySelect -> {
                state = state.copy(selection = SelectionState.TO)
            }

            ScreenEvent.SwapIconClicked -> {
                state = state.copy(
                    currencyCode = state.toCurrencyCode,
                    currencyValue = state.toCurrencyValue,
                    toCurrencyCode = state.currencyCode,
                    toCurrencyValue = state.currencyValue
                )
            }
            is ScreenEvent.ButtonClickNumber -> {
                updateValue(value = event.value)
            }
            is ScreenEvent.BottomSheetClickItem -> {
                if (state.selection == SelectionState.FROM){
                    state = state.copy(currencyCode = event.value)
                }else if (state.selection == SelectionState.TO) {
                    state = state.copy(toCurrencyCode = event.value)
                }
                updateValue("")
            }
        }
    }

    private fun getCurrencyList() {
        viewModelScope.launch {
            repository
                .getListCurrent()
                .collectLatest { result ->
                    state = when (result) {
                        is Resourse.Success -> {
                             state.copy(
                                exchangeRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                error = null
                            )
                        }

                        is Resourse.Error -> {
                            state.copy(
                                exchangeRates = result.data?.associateBy { it.code } ?: emptyMap(),
                                error = result.message
                            )
                        }
                    }
                }
        }
    }

    private fun updateValue(value: String) {
        val currentCurrencyValue = when (state.selection) {
            SelectionState.FROM -> state.currencyValue
            SelectionState.TO -> state.toCurrencyValue
        }
        val fromCurrencyRate = state.exchangeRates[state.currencyCode]?.rate ?: 0.0
        val toCurrencyRate = state.exchangeRates[state.toCurrencyCode]?.rate ?: 0.0

        val updateValue = when(value) {
            "C" -> "0.00"
            else -> if (currentCurrencyValue == "0.00") value else currentCurrencyValue + value
        }

        val numberStyle = DecimalFormat("#.00")
        when (state.selection) {
            SelectionState.FROM -> {
                val fromValue = updateValue.toDoubleOrNull()?: 0.0
                val toValue = fromValue / fromCurrencyRate * toCurrencyRate
                state = state.copy(
                    currencyValue = updateValue,
                    toCurrencyValue = numberStyle.format(toValue)
                )

            }

            SelectionState.TO -> {
                val toValue = updateValue.toDoubleOrNull()?: 0.0
                val fromValue = toValue / toCurrencyRate * fromCurrencyRate
                state = state.copy(
                    toCurrencyValue = updateValue,
                    currencyValue = numberStyle.format(fromValue)
                )

            }
        }
    }
}
