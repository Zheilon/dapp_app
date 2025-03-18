package com.zhei.dapp.view.viewmodels
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zhei.dapp.COMPLEMENT
import com.zhei.dapp.DIFERENCIA
import com.zhei.dapp.INTERCCION
import com.zhei.dapp.SUB_SET
import com.zhei.dapp.UNION
import com.zhei.dapp.data.models.SetsEntity
import com.zhei.dapp.data.models.SetsResultsEntity
import com.zhei.dapp.data.repository.CommonActions
import com.zhei.dapp.data.repository.SetsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SetsScreenViewModel : ViewModel() {

    private val repositorySets = SetsRepository()
    private val repositoryCommon = CommonActions()

    private val _listSets = MutableStateFlow<List<SetsEntity>>(emptyList())
    val listSets: StateFlow<List<SetsEntity>> = _listSets

    private val _resultSet = MutableStateFlow(SetsResultsEntity())
    val resultSet: StateFlow<SetsResultsEntity> = _resultSet

    private val _fieldFormula = mutableStateOf("")
    val fieldFormula: State<String> = _fieldFormula

    private val _universalSet = MutableStateFlow<Set<Any>>(emptySet())
    val universalSet: StateFlow<Set<Any>> = _universalSet

    fun create ()
    {
        viewModelScope.launch (Dispatchers.IO) {
            if (_listSets.value.isEmpty())  {
                _listSets.value = listOf(
                    SetsEntity(repositorySets.createKeyName(_listSets.value), emptySet())
                )
            }
            else _listSets.value += listOf(SetsEntity(repositorySets.createKeyName(_listSets.value)))
        }
    }


    fun delete ()
    {
        viewModelScope.launch (Dispatchers.IO) {
            _listSets.value = repositorySets.deleteLast(_listSets.value)
        }
    }


    fun update (keyName: String, set: Set<Any>)
    {
        viewModelScope.launch (Dispatchers.IO) {
            _listSets.emit(
                _listSets.value.map {
                    if (keyName == it.keyName) it.copy(set = set) else it
                }
            )
        }
    }


    fun addKeyNameOnField (keyName: String) { _fieldFormula.value += keyName }

    fun addUnion () { _fieldFormula.value += UNION }

    fun addIntersect () { _fieldFormula.value += INTERCCION }

    fun addDifference () { _fieldFormula.value += DIFERENCIA }

    fun addSubSet () { _fieldFormula.value += SUB_SET }

    fun addComplement () { _fieldFormula.value += COMPLEMENT }

    fun deleteLastOnField () { _fieldFormula.value = _fieldFormula.value.dropLast(1) }

    fun addCorcheteWith ()
    {
        _fieldFormula.value += repositoryCommon.lastCharSeparators(
            symbolInit = '[',
            symbolFinal = ']',
            content = _fieldFormula.value
        )
    }


    fun addLlavesWith ()
    {
        _fieldFormula.value += repositoryCommon.lastCharSeparators(
            symbolInit = '{',
            symbolFinal = '}',
            content = _fieldFormula.value
        )
    }


    fun addParentesisWith ()
    {
        _fieldFormula.value += repositoryCommon.lastCharSeparators(
            symbolInit = '(',
            symbolFinal = ')',
            content = _fieldFormula.value
        )
    }


    fun getSimplexAnalizer ()
    {
        _resultSet.value = repositorySets.splitSetsSimplex(
            expression = _fieldFormula.value,
            sets = _listSets.value
        )
    }


    fun updateUniversalSet () {
        viewModelScope.launch (Dispatchers.Main) {
            _universalSet.value = repositorySets.createUniversalSet(_listSets.value)
        }
    }

}