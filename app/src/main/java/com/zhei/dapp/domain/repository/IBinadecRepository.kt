package com.zhei.dapp.domain.repository

import com.zhei.dapp.data.models.BinadecsEntity
import com.zhei.dapp.data.models.TransformsBEntity

interface IBinadecRepository {


    /*--------------------- From Decimal --------------------------*/

    fun fromDecimalToBinary(decimal: String) : String

    fun fromDecimalToOctal(decimal: String) : String

    fun fromDecimalToHex(decimal: String) : String

    /*--------------------- From Binary --------------------------*/

    fun fromBinaryToDecimal(binary: String) : String

    fun fromBinaryToOctal(binary: String) : String

    fun fromBinaryToHexa(binary: String) : String

    /*--------------------- From Hex --------------------------*/

    fun fromHexToDecimal(hex: String) : String

    fun fromHexToBinary(hex: String) : String

    fun fromHexToOctal(hex: String) : String

    fun isHex(hex: String) : Boolean

    fun isBinary(binary: String) : Boolean

    fun fromOneToGetAll(expression: String, isDecimal: Boolean) : List<BinadecsEntity>

    fun getTransformWithSum(expression: String) : List<TransformsBEntity>

}