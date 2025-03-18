package com.zhei.dapp

import com.zhei.dapp.data.models.SetsEntity
import com.zhei.dapp.data.repository.CommonActions
import com.zhei.dapp.data.repository.SetsRepository
import com.zhei.dapp.data.repository.TablesRepository

fun main ()
{
    val sets = listOf(
        SetsEntity("A", setOf(2, 3, 5, 1, 7)),
        SetsEntity("B", setOf(6, 7, 8, 1, 4)),
        SetsEntity("C", setOf(1, 2, 3, 4, 5))
    )
    val expresion = "A${DIFERENCIA}B${UNION}C${DIFERENCIA}A"
    println(SetsRepository().rawPattern(expression = expresion, entities = sets))
}