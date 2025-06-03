package com.zhei.dapp
import kotlin.math.abs

fun main ()
{
    decodeAlien("BA")
}

fun decodeAlien(value: String): Int
{
    var sum = 0
    val language: () -> Unit = { println("Print!") }
    val abcMap = (97..122).map { mapOf(Char(it).toString().lowercase() to abs(97 - it)) }
    val valueSplit = value.map { it.toString().lowercase() }
    return 0
}