package com.zhei.dapp.domain.repository

interface ITablesRepository {

    fun getQuantityVars (expression: String) : Int

    fun getSizeWithSquare (expression: String) : Int

    fun generateTable (expression: String) : List<List<Int>>

}