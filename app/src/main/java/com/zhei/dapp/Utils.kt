package com.zhei.dapp

object Utils {


    fun getOnlyAllLetters () : List<Char>
    {
        return listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
            'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z')
    }


    /*Retiro la U, ya que esta hace referencia al conjunto Universal*/
    fun getOnlyUpperLetters () : List<Char>
    {
        return listOf('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'V', 'W', 'X', 'Y', 'Z')
    }
}