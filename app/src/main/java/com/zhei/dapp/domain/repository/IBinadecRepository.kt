package com.zhei.dapp.domain.repository

interface IBinadecRepository {


    fun fromDecimalToBinary(decimal: String): String

    fun fromBinaryToDecimal (binary: String) : String

}