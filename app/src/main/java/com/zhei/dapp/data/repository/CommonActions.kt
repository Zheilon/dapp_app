package com.zhei.dapp.data.repository
import com.zhei.dapp.data.models.TablesEntity
import com.zhei.dapp.domain.repository.ICommonActions

open class CommonActions : ICommonActions {

    override fun getASCIIChars(): List<Char>
    {
        return (60..140).map { Char(it) }
    }


    override fun square (base: Int, exponent: Int) : Int
    {
        var c = 1
        (0..<exponent).forEach { _ -> c *= base }
        return c
    }


    override fun lastCharSeparators(symbolInit: Char, symbolFinal: Char, content: String): Char
    {
        val list = content.filter { it == symbolInit || it == symbolFinal }.toList()
        val confirm = if (list.isNotEmpty()) list.last() else ' '

        return if (!content.contains(symbolInit)) { symbolInit
        } else if (confirm == symbolInit) { symbolFinal
        } else if (confirm == symbolFinal) { symbolInit
        } else { ' ' }
    }


    override fun getParticularExpression(list: List<Char>, indexDivide: Int): String
    {
        println("<------------------------------->")
        var c = ""
        list.forEachIndexed { index, item ->
            if (index < indexDivide) {
                c += item
            }
        }

        println("List Arrived: $list")
        println("Chars Cut: $c")
        println("Cut Factor: $indexDivide")
        println("<------------------------------->")

        return c
    }


    override fun <T> superOrderByDescending(item: List<T>) : List<T>
    {
        return List(item.size) { index -> item[(item.size - 1) - index] }
    }


}