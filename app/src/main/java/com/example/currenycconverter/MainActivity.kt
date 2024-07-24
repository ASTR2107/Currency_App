package com.example.currenycconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.example.currenycconverter.View.MainViewModel
import com.example.currenycconverter.View.ScreenView
import com.example.currenycconverter.ui.theme.CurrenycConverterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrenycConverterTheme {
                val mainViewModel: MainViewModel by viewModels()
                Surface {
                    ScreenView(
                        state = mainViewModel.state,
                        onEvent = mainViewModel::onEvent
                    )
                }
            }
        }
    }
}

