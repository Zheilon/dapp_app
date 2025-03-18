package com.zhei.dapp.data.repository

import com.zhei.dapp.domain.repository.IBinadecRepository

class BinadecRepository : IBinadecRepository {


    override fun fromDecimalToBinary(decimal: String): String
    {
        return if (decimal.isNotEmpty()) {
            decimal.toBigInteger().toString(2)
        } else {
            "Ingresa número decimal!"
        }

    }

    override fun fromBinaryToDecimal(binary: String): String
    {
        return if ((binary.all { it == '0' || it == '1' }) && binary.isNotEmpty()) {
            binary.toBigInteger(2).toString()
        } else {
            "Ingresa número binario!"
        }
    }


}