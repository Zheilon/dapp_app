package com.zhei.dapp.view.viewmodels
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.zhei.dapp.data.models.BinadecsClasses
import com.zhei.dapp.data.models.BinadecsEntity
import com.zhei.dapp.data.repository.BinadecRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BinadecScreenViewModel : ViewModel() {

    private val repositoryBina = BinadecRepository()

    var onPressDecimal by mutableStateOf(false)
        private set

    var onPressBinary by mutableStateOf(false)
        private set

    var onPressOcta by mutableStateOf(false)
        private set

    var onPressHex by mutableStateOf(false)
        private set

    var onPressComplement2 by mutableStateOf(false)
        private set

    private val _text = mutableStateOf("")
    val text: State<String> = _text

    private val _listTransforms = MutableStateFlow(listOf<BinadecsEntity>())
    val listTransforms: StateFlow<List<BinadecsEntity>> = _listTransforms.asStateFlow()



    fun updateText (value: String) { _text.value += value }

    fun deleteText() { _text.value = _text.value.dropLast(1)}


    fun updateOnPressDecimal()
    {
        onPressDecimal = !onPressDecimal

        if (onPressBinary) onPressBinary = !onPressBinary
        if (onPressOcta) onPressOcta = !onPressOcta
        if (onPressHex) onPressHex = !onPressHex
        if (onPressComplement2) onPressComplement2 = !onPressComplement2
    }


    fun updateOnPressBinary()
    {
        onPressBinary = !onPressBinary

        if (onPressDecimal) onPressDecimal = !onPressDecimal
        if (onPressOcta) onPressOcta = !onPressOcta
        if (onPressHex) onPressHex = !onPressHex
        if (onPressComplement2) onPressComplement2 = !onPressComplement2

    }


    fun executeTransform ()
    {
        if (_listTransforms.value.isNotEmpty()) { _listTransforms.value = emptyList() }

        /*Cambiar ese isDecimal!*/
        _listTransforms.value = repositoryBina.fromOneToGetAll(expression = _text.value, isDecimal = true)

    }

}