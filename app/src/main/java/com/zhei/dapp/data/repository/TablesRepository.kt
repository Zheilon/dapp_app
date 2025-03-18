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
import kotlin.math.exp

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


    override fun generateTableStart(expression: String): List<TablesEntity>
    {
        val quatityVars = getQuantityVars(expression)
        println("Quantity vars = $quatityVars")

        val getSize = getSizeWithSquare(expression)
        println("Scale = $getSize")

        val alphabetOrder = extractVarsAlphabetOrder(expression)

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


    override fun rawPattern(expression: String): List<TablesIterSaver>
    {
        val specialChars = getSpecialChars(expression)
        println("Specials: $specialChars")

        val iterExpression = expression.map { it.toString() }
        println("Itered: $iterExpression")

        specialChars

        return listOf(TablesIterSaver())
    }






}