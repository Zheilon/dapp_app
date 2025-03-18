package com.zhei.dapp.view.viewmodels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    var translationTableX by mutableFloatStateOf(0f)
        private set

    var translationSetX by mutableFloatStateOf(1f)
        private set

    var translationBinadec by mutableFloatStateOf(0f)
        private set

    var translationTableTitleX by mutableFloatStateOf(1f)
        private set

    var translationSetsTitleY by mutableFloatStateOf(0f)
        private set

    var translationBinadecTitleX by mutableFloatStateOf(1f)
        private set

    var screenAcivated by mutableStateOf(false)
        private set

    var buttonBoxPressed by mutableStateOf(false)
        private set


    fun isActivated () {
        viewModelScope.launch {
            delay(1200)
            screenAcivated = true
        }
    }

    fun updateButtonPressed (value: Boolean) { buttonBoxPressed = value }
}