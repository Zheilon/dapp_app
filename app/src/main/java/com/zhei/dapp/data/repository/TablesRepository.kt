package com.zhei.dapp.data.repository
import com.zhei.dapp.Utils
import com.zhei.dapp.domain.repository.ITablesRepository
import kotlin.math.pow

class TablesRepository : ITablesRepository, CommonActions() {


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


    override fun generateTable(expression: String): List<List<Int>>
    {
        val quatityVars = getQuantityVars(expression)
        val getSize = getSizeWithSquare(expression)

        var less = 0
        var high = 0
        var trust: Boolean
        var hh2 = 0

        return (0..<quatityVars).mapIndexed outer@ { index, _ ->
            (0..<getSize).mapIndexed inner@ { mInd, _  ->
                if (index == 0) {
                    val value = if (less < square(2, index)) {
                        less += 1
                        0
                    } else {
                        less = 0
                        1
                    }

                    return@inner value

                } else if (index > 0) {
                    high += 1
                    if (high < square(2, index)) {
                        trust = true
                    } else {
                        trust = false
                        high = 0
                    }

                    val value = if (trust) 0 else 1


                    return@inner value
                }

                1
            }
        }
    }


    /**
     *         val value = if (high < square(2, index)) {
     *                         println("high: $high - square: ${square(2, index)}")
     *                         0
     *                     } else {
     *                         if (high < multiply(2, index)) {
     *                             1
     *                         } else {
     *                             high = 0
     *                             0
     *                         }
     *                     }
     *                     high += 1
     */

}