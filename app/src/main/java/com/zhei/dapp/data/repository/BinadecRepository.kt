package com.zhei.dapp.data.repository
import com.zhei.dapp.data.models.BinadecsClasses
import com.zhei.dapp.data.models.BinadecsEntity
import com.zhei.dapp.data.models.TransformsBEntity
import com.zhei.dapp.domain.repository.IBinadecRepository
import java.math.BigInteger
import kotlin.math.exp


class BinadecRepository : IBinadecRepository {


    override fun fromBinaryToDecimal(binary: String): String
    {
        return if ((binary.all { it == '0' || it == '1' }) && binary.isNotEmpty())
            binary.toBigInteger(2).toString() else ""
    }


    override fun fromBinaryToOctal(binary: String): String
    {
        return if (binary.all { it == '0' || it == '1'} && binary.isNotEmpty())
            binary.toBigInteger(2).toString(8) else ""
    }


    override fun fromBinaryToHexa(binary: String): String
    {
        return if (binary.all { it == '0' || it == '1' } && binary.isNotEmpty())
            binary.toBigInteger(2).toString(16).uppercase() else ""
    }


    override fun fromDecimalToBinary(decimal: String): String
    {
        return if (decimal.isNotEmpty()) decimal.toBigInteger().toString(2) else ""
    }


    override fun fromDecimalToOctal(decimal: String): String
    {
        return if (decimal.isNotEmpty()) decimal.toBigInteger().toString(8) else ""
    }


    override fun fromDecimalToHex(decimal: String): String
    {
        return if (decimal.isNotEmpty()) decimal.toBigInteger().toString(16) else ""
    }


    override fun fromHexToDecimal(hex: String): String
    {
        return if (hex.isNotEmpty()) hex.toBigInteger(16).toString(10) else ""
    }


    override fun fromHexToOctal(hex: String): String
    {
        return if (hex.isNotEmpty()) hex.toBigInteger(16).toString(8) else ""
    }


    override fun fromHexToBinary(hex: String): String
    {
        return if (hex.isNotEmpty())
        hex.toBigInteger(16).toString(2) else ""
    }


    override fun isBinary(binary: String): Boolean
    {
        return binary.all { it == '0' || it == '1' }
    }


    override fun isHex(hex: String): Boolean
    {
        val have: (String) -> Boolean = { hex.lowercase().contains(it) }
        return (have("a") || have("b") || have("c") || have("d") || have("e") || have("f"))
    }


    override fun fromOneToGetAll(expression: String, isDecimal: Boolean) : List<BinadecsEntity>
    {
        val result = mutableListOf<BinadecsEntity>()

        if (isHex(expression)) {
            (0..2).forEach {
                result.add(
                    when (it) {
                        0 -> BinadecsEntity(
                            type = BinadecsClasses.DECIMAL_EN,
                            resultOf = fromHexToDecimal(expression)
                        )
                        1 -> BinadecsEntity(
                            type = BinadecsClasses.BINARY_EN,
                            resultOf = fromHexToBinary(expression)
                        )
                        2 -> BinadecsEntity(
                            type = BinadecsClasses.OCTAL_EN,
                            resultOf = fromHexToOctal(expression)
                        )
                        else -> BinadecsEntity(BinadecsClasses.NULL)
                    }
                )
            }
        }

        /*i put here isDecimal like: !isDecimal*/
        if (expression.all { it == '1' || it == '0' } && !isDecimal) {
            (0..2).forEach {
                result.add(
                    when (it) {
                        0 -> BinadecsEntity(
                            type = BinadecsClasses.DECIMAL_EN,
                            resultOf = fromBinaryToDecimal(expression)
                        )
                        1 -> BinadecsEntity(
                            type = BinadecsClasses.OCTAL_EN,
                            resultOf = fromBinaryToOctal(expression)
                        )
                        2 -> BinadecsEntity(
                            type = BinadecsClasses.HEX_EN,
                            resultOf = fromBinaryToHexa(expression).uppercase()
                        )
                        else -> BinadecsEntity(BinadecsClasses.NULL)
                    }
                )
            }
        }

        if ((!expression.all { it == '1' || it == '0' } || isDecimal) && !isHex(expression)) {
            (0..2).forEach {
                result.add(
                    when (it) {
                        0 -> BinadecsEntity(
                            type = BinadecsClasses.BINARY_EN,
                            resultOf = fromDecimalToBinary(expression)
                        )
                        1 -> BinadecsEntity(
                            type = BinadecsClasses.OCTAL_EN,
                            resultOf = fromDecimalToOctal(expression)
                        )
                        2 -> BinadecsEntity(
                            type = BinadecsClasses.HEX_EN,
                            resultOf = fromDecimalToHex(expression).uppercase()
                        )
                        else -> BinadecsEntity(BinadecsClasses.NULL)
                    }
                )
            }
        }

        return result.toList()
    }
    

    override fun getTransformWithSum(expression: String): List<TransformsBEntity>
    {
        val list = mutableListOf<TransformsBEntity>()

        if (expression.toBigIntegerOrNull() != null) {
            val exp = 70
            var exprInt = if (expression.isNotEmpty()) expression.toBigInteger() else BigInteger("0")
            val square: (Int) -> BigInteger = { BigInteger("2").pow(it) }

            (exp downTo 0).forEach { sqr ->
                val squared = square(sqr)
                if (exprInt >= squared) {
                    list.add(TransformsBEntity(
                        elevateExp = sqr.toString(),
                        result = "$exprInt - $squared = ${exprInt - squared}")
                    )
                    exprInt -= squared
                }
            }
        }

        if (isBinary(expression)) { return emptyList() }

        return list
    }

}