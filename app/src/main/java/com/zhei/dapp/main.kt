package com.zhei.dapp

import com.zhei.dapp.data.models.SetsEntity
import com.zhei.dapp.data.repository.CommonActions
import com.zhei.dapp.data.repository.SetsRepository
import com.zhei.dapp.data.repository.TablesRepository

fun main ()
{
    val sets = listOf(
        SetsEntity("A", setOf("t", "u", "ñ", 0, "d")),
        SetsEntity("B", setOf("u", 0)),
        SetsEntity("C", setOf("k", "n", "I", "I", "n"))
    )
    val expresion = "${NEGACION}Ñ${BICONDICIONAL}R${DISYUNCION}P"
    println(TablesRepository().rawPattern(expresion))
    println(TablesRepository().negation(0))
}