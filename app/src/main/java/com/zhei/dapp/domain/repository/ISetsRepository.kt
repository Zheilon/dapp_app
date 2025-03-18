package com.zhei.dapp.domain.repository
import com.zhei.dapp.data.models.SetsEntity
import com.zhei.dapp.data.models.SetsIterSaver
import com.zhei.dapp.data.models.SetsResultsEntity

interface ISetsRepository {

    fun getDecodeExpression (expression: String) : List<Char>

    fun rawPattern (expression: String, entities: List<SetsEntity>) : List<SetsIterSaver>

    fun isSet (expresion: String) : Boolean

    fun splitSetsSimplex (expression: String, sets: List<SetsEntity>) : SetsResultsEntity

    fun union (a: Set<Any>, b: Set<Any>) : Set<Any>

    fun intersect (a: Set<Any>, b: Set<Any>) : Set<Any>

    fun difference (a: Set<Any>, b: Set<Any>) : Set<Any>

    fun subSet (a: Set<Any>, b: Set<Any>) : Set<Any>

    fun complement (universalSet: Set<Any>, b: Set<Any>) : Set<Any>

    fun createUniversalSet (set: List<SetsEntity>) : Set<Any>

    fun createKeyName (list: List<SetsEntity>) : String

    fun deleteLast (list: List<SetsEntity>) : List<SetsEntity>

    fun getQuantityOperations (expression: String) : Int

    fun getSpecialCharacters (expression: String) : List<Char>

    fun getParticularExpression (list: List<Char>, indexDivide: Int) : String

    fun simpleIter (iterable: String) : List<String>

}