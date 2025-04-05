package com.zhei.dapp.data.repository
import com.zhei.dapp.BICONDICIONAL
import com.zhei.dapp.CONDICIONAL
import com.zhei.dapp.CONJUNCION
import com.zhei.dapp.DISYUNCION
import com.zhei.dapp.NEGACION
import com.zhei.dapp.Utils
import com.zhei.dapp.data.models.TablesEntity
import com.zhei.dapp.data.models.TablesIterSaver
import com.zhei.dapp.domain.repository.ITablesRepository

class TablesRepository : ITablesRepository, CommonActions() {


    override fun conjunction(a: Any, b: Any): Int
    {
        return if (a == 1 && b == 1) 1 else 0
    }


    override fun disjunction(a: Any, b: Any): Int
    {
        return if (a == 0 && b == 0) 0 else 1
    }


    override fun conditional(a: Any, b: Any): Int
    {
        return if (a == 1 && b == 0) 0 else 1
    }


    override fun biconditional(a: Any, b: Any): Int
    {
        return if (a == b) 1 else 0
    }


    override fun negation(z: Any): Int
    {
        return if (z == 1) 0 else 1
    }


    override fun getQuantityVars(expression: String): Int
    {
        var increment = 0
        for (z in expression) {
            Utils.getOnlyAllLetters().forEach {
                if (z == it) {
                    increment += 1
                }
            }
        }
        return increment
    }


    override fun getSizeWithSquare(expression: String): Int
    {
        var c = 1
        (1..getQuantityVars(expression)).forEach { _ -> c *= 2 }
        return c
    }


    override fun extractVarsAlphabetOrder(expression: String): List<String>
    {
        return Utils.getOnlyAllLetters().filter { it in expression }.map { it.toString() }
    }


    override fun getSpecialChars(expression: String): List<String>
    {
        return expression.filter {
            it.toString() == CONJUNCION || it.toString() == DISYUNCION ||
                    it.toString() == CONDICIONAL || it.toString() == BICONDICIONAL ||
                    it.toString() == NEGACION
        }.map { it.toString() }
    }


    override fun takeChar(list: List<String>): String
    {
        val available = Utils.getOnlyAllLetters().filterNot { it.toString() in list }
        return if (available.isNotEmpty()) available[(0..<available.size - 1).random()].toString()
        else ""
    }


    override fun comparatorToDo (
        entity: TablesEntity, oldList: List<Int>,
        currentList: List<Int>, toDo: String
    ) : List<Int>
    {
        return when (toDo) {
            NEGACION ->
                entity.listVar.map {
                    negation(it)
                }

            CONJUNCION ->
                oldList.zip(currentList) { old, current ->
                    conjunction(old, current)
                }

            DISYUNCION ->
                oldList.zip(currentList) { old, current ->
                    disjunction(old, current)
                }

            CONDICIONAL ->
                oldList.zip(currentList) { old, current ->

                    /*Hice un cambio: antes estaba: old, current*/
                    conditional(current, old)
                }

            BICONDICIONAL ->
                oldList.zip(currentList) { old, current ->
                    biconditional(old, current)
                }

            else -> emptyList()
        }
    }


    override fun generateTableStart(expression: String): List<TablesEntity>
    {
        val quatityVars = getQuantityVars(expression)
        println("Quantity vars = $quatityVars")

        val getSize = getSizeWithSquare(expression)
        println("Scale = $getSize")

        val alphabetOrder = extractVarsAlphabetOrder(expression)
        println()

        val general = mutableListOf<TablesEntity>()
        var less = 0

        (0..<quatityVars).forEachIndexed { index, outer ->
            val incFactor = square(2, outer)
            val innerList = mutableListOf<Int>()

            (0..<getSize).forEach { _ ->
                if (outer == 0) {
                    if (less < incFactor) {
                        innerList.add(0)
                        less += 1

                    } else {
                        innerList.add(1)
                        less = 0
                    }
                } else {

                    if (less < incFactor) {
                        innerList.add(0)
                        less += 1

                    } else {
                        var c = 0
                        while (c < incFactor) {
                            innerList.add(1)
                            c += 1
                        }

                        less = 0
                    }
                }
            }

            less = 0
            val order = (alphabetOrder.size - 1) - index

            if (innerList.isNotEmpty()) {
                general.add(
                    TablesEntity(
                        variable = alphabetOrder[order],
                        listVar = innerList.dropLast(innerList.size - getSize)
                    )
                )
            }
        }

        return general
    }


    override fun rawPattern(expression: String): List<TablesEntity>
    {
        val specialChars = getSpecialChars(expression)
        println("Specials: $specialChars")

        var iterExpression = expression.map { it }
        println("Itered: $iterExpression")

        val copyIter = expression.map { it }
        println("${copyIter.size}")

        val tableStarting = superOrderByDescending(generateTableStart(expression)).toMutableList()

        var listLazilyExpressions = mutableListOf<TablesIterSaver>()

        specialChars.forEach { _ ->
            iterExpression.forEach { _ ->
                if (iterExpression.isNotEmpty()) {
                    val cuteFactor = if (iterExpression.size % 2 == 0) 2 else 3
                    listLazilyExpressions.add(
                        TablesIterSaver(
                            expression = getParticularExpression(list = iterExpression, cuteFactor)
                        )
                    )
                    iterExpression = iterExpression.drop(cuteFactor)
                }
            }
        }

        listLazilyExpressions.forEachIndexed { _, item ->
            /*Remover valores para actualizar size.*/
            val cuteFactor = if (iterExpression.size % 2 == 0) 2 else 3

            if (copyIter.size % 2 == 0) {

                val iteratedValues = item.expression.map { it.toString() }
                val first = tableStarting.filter { it.variable == iteratedValues.last() }[0]
                val lenToUse = tableStarting.size - 1 == listLazilyExpressions.size - 1

                val entityToUse = if (lenToUse) first else tableStarting.last()
                val textToUse = if (lenToUse) item.expression else "(${entityToUse.variable})${item.expression}"

                if (item.expression.contains(NEGACION)) {
                    tableStarting.add(
                        TablesEntity(
                            variable = textToUse,
                            listVar = comparatorToDo(
                                entity = entityToUse,
                                toDo = NEGACION
                            )
                        )
                    )
                }

                listOf(CONJUNCION, DISYUNCION, CONDICIONAL, BICONDICIONAL).forEach {
                    if (item.expression.contains(it)) {
                        tableStarting.add(
                            TablesEntity(
                                variable = textToUse,
                                listVar = comparatorToDo(
                                    entity = entityToUse,
                                    oldList = first.listVar,
                                    currentList = entityToUse.listVar,
                                    toDo = it
                                )
                            )
                        )
                    }
                }

                val l = listLazilyExpressions.toList()
                listLazilyExpressions = l.drop(cuteFactor).toMutableList()

            }
        }

        println("\n<------------- Table ------------->\n$tableStarting\n")
        println("<------------- Checker ------------->\n$listLazilyExpressions")

        return tableStarting
    }






}