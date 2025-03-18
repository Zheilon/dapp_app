package com.zhei.dapp.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.zhei.dapp.data.repository.BinadecRepository
import com.zhei.dapp.data.repository.CommonActions
import com.zhei.dapp.ui.theme.DappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DappTheme {
                MyNavGraph()
            }
        }
    }
}