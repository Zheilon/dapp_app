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
import com.zhei.dapp.data.models.TransformsBEntity
import com.zhei.dapp.data.repository.BinadecRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BinadecScreenViewModel : ViewModel() {

    private val repositoryBina = BinadecRepository()

    var isDecimal by mutableStateOf(false)
        private set

    private val _text = mutableStateOf("")
    val text: State<String> = _text

    private val _listTransforms = MutableStateFlow(listOf<BinadecsEntity>())
    val listTransforms: StateFlow<List<BinadecsEntity>> = _listTransforms.asStateFlow()

    private val _listWithDetailPross = MutableStateFlow(listOf<TransformsBEntity>())
    val listWithDetailPross: StateFlow<List<TransformsBEntity>> = _listWithDetailPross.asStateFlow()


    fun updateIsDecimal () { isDecimal = !isDecimal }

    fun updateText (value: String) { _text.value += value }

    fun deleteText () { _text.value = _text.value.dropLast(1)}


    fun executeTransform ()
    {
        if (_listTransforms.value.isNotEmpty()) { _listTransforms.update { emptyList() } }
        _listTransforms.update { repositoryBina.fromOneToGetAll(expression = _text.value, isDecimal = isDecimal) }
    }

    fun executeDetailTransform ()
    {
        _listWithDetailPross.update { repositoryBina.getTransformWithSum(_text.value) }
    }

}