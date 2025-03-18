package com.zhei.dapp.domain.repository

import com.zhei.dapp.data.models.TablesEntity
import com.zhei.dapp.data.models.TablesIterSaver

interface ITablesRepository {

    fun conjunction (a: Any, b: Any) : Int

    fun disjunction (a: Any, b: Any) : Int

    fun conditional (a: Any, b: Any) : Int

    fun biconditional (a: Any, b: Any) : Int

    fun getQuantityVars (expression: String) : Int

    fun getSizeWithSquare (expression: String) : Int

    fun generateTableStart (expression: String) : List<TablesEntity>

    fun rawPattern (expression: String) : List<TablesIterSaver>

    fun extractVarsAlphabetOrder (expression: String) : List<String>

    fun getSpecialChars (expression: String) : List<String>

}