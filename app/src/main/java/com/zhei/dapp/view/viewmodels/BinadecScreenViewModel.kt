package com.zhei.dapp.view.viewmodels
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zhei.dapp.data.repository.BinadecRepository

class BinadecScreenViewModel : ViewModel() {

    private val repositoryBina = BinadecRepository()

    var onPressChangerArrows by mutableStateOf(false)
        private set

    private val _text = mutableStateOf("")
    val text: State<String> = _text

    private val _binary = mutableStateOf("")
    val binary: State<String> = _binary

    private val _decimal = mutableStateOf("")
    val decimal: State<String> = _decimal


    fun updateChangerArrows (value: Boolean) { onPressChangerArrows = value }

    fun updateText (value: String) { _text.value = value }


    fun getResultTransform ()
    {
        if (!onPressChangerArrows) {
            _decimal.value = repositoryBina.fromBinaryToDecimal(text.value)

        } else {
            _binary.value = repositoryBina.fromDecimalToBinary(text.value)
        }
    }

}