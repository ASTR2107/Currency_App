package com.example.currenycc
 sealed class ScreenEvent {
     object FromCurrencySelect: ScreenEvent()
     object ToCurrencySelect: ScreenEvent()
     object SwapIconClicked: ScreenEvent()
     data class BottomSheetClickItem(val value: String): ScreenEvent()
     data class ButtonClickNumber(val value: String): ScreenEvent()



 }