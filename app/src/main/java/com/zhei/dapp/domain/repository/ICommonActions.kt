package com.zhei.dapp.domain.repository

interface ICommonActions {

    fun getASCIIChars() : List<Char>

    fun square(base: Int, exponent: Int) : Int

    fun lastCharSeparators(symbolInit: Char, symbolFinal: Char, content: String): Char

    fun getParticularExpression(list: List<Char>, indexDivide: Int): String

}