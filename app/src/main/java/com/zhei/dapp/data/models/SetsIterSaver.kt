package com.zhei.dapp.data.models

data class SetsIterSaver(

    var oldExpression: String = "",
    val resultSet: Set<Any> = emptySet()
) {

    fun updateExpression (value: String) { oldExpression += value }
}
