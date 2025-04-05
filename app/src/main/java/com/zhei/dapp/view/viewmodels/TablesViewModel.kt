package com.zhei.dapp.view.viewmodels
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhei.dapp.BICONDICIONAL
import com.zhei.dapp.CONDICIONAL
import com.zhei.dapp.CONJUNCION
import com.zhei.dapp.DISYUNCION
import com.zhei.dapp.NEGACION
import com.zhei.dapp.data.models.TablesEntity
import com.zhei.dapp.data.repository.CommonActions
import com.zhei.dapp.data.repository.TablesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TablesViewModel : ViewModel() {

    private val commonRepository = CommonActions()
    private val tablesRepository = TablesRepository()

    private val _listResults = MutableStateFlow<List<TablesEntity>>(emptyList())
    val listResults: StateFlow<List<TablesEntity>> = _listResults.asStateFlow()

    private val _listOfVars = MutableStateFlow<List<String>>(emptyList())
    val listOfVars: StateFlow<List<String>> = _listOfVars.asStateFlow()

    private val _fieldText = mutableStateOf("")
    val fieldText: State<String> = _fieldText

    var onPressGenerate by mutableStateOf(false)
        private set


    fun addVar (value: String) { _fieldText.value += value }

    fun addNegation () { _fieldText.value += NEGACION }

    fun addConjunction () { _fieldText.value += CONJUNCION }

    fun addDisjunction () { _fieldText.value += DISYUNCION }

    fun addConditional () { _fieldText.value += CONDICIONAL }

    fun addBiconditional () { _fieldText.value += BICONDICIONAL }

    fun deleteFieldVar () { _fieldText.value = _fieldText.value.dropLast(1) }

    fun updateOnPressGenerate (bool: Boolean) { onPressGenerate = bool }

    fun addParentesisWith ()
    {
        _fieldText.value += commonRepository
        .lastCharSeparators('(', ')', _fieldText.value)
    }

    fun addCorChetesWith ()
    {
        _fieldText.value += commonRepository
        .lastCharSeparators('[', ']', _fieldText.value)
    }

    fun addLlavesWith ()
    {
        _fieldText.value += commonRepository
        .lastCharSeparators('{', '}', _fieldText.value)
    }


    fun generateVar ()
    {
        _listOfVars.update { it + tablesRepository.takeChar(_listOfVars.value) }
    }


    fun generate ()
    {
        _listResults.value = tablesRepository.rawPattern(_fieldText.value)
    }

}