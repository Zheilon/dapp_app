package com.zhei.dapp.data.repository
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


}