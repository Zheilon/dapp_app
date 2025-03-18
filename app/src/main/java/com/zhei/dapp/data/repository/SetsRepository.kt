package com.zhei.dapp.data.repository
import com.zhei.dapp.COMPLEMENT
import com.zhei.dapp.DIFERENCIA
import com.zhei.dapp.INTERCCION
import com.zhei.dapp.SUB_SET
import com.zhei.dapp.UNION
import com.zhei.dapp.Utils
import com.zhei.dapp.VACIO
import com.zhei.dapp.data.models.SetsEntity
import com.zhei.dapp.data.models.SetsIterSaver
import com.zhei.dapp.data.models.SetsResultsEntity
import com.zhei.dapp.domain.repository.ISetsRepository

class SetsRepository : ISetsRepository {

    override fun getDecodeExpression(expression: String): List<Char>
    {
        return expression.map { it }.filter { it != ' ' }
    }


    override fun union (a: Set<Any>, b: Set<Any>) : Set<Any>
    {
        return a union b
    }


    override fun intersect(a: Set<Any>, b: Set<Any>): Set<Any>
    {
        return if ((a intersect b).isNotEmpty()) a intersect b else setOf(VACIO)
    }


    override fun difference(a: Set<Any>, b: Set<Any>): Set<Any>
    {
        return if ((a subtract b).isNotEmpty()) a subtract b else setOf(VACIO)
    }


    override fun subSet(a: Set<Any>, b: Set<Any>): Set<Any>
    {
        return if (a.containsAll(b)) b else setOf(VACIO)
    }


    override fun complement(universalSet: Set<Any>, b: Set<Any>): Set<Any>
    {
        return difference(universalSet, b).ifEmpty { setOf(VACIO) }
    }


    override fun getQuantityOperations(expression: String): Int
    {
        return expression.fold(0) { acc, c ->
            when (c.toString()) {
                UNION -> acc + 1
                INTERCCION -> acc + 1
                DIFERENCIA -> acc + 1
                SUB_SET -> acc + 1
                COMPLEMENT -> acc + 1
                else -> acc
            }
        }
    }


    override fun getSpecialCharacters(expression: String): List<Char>
    {
        return expression.filter {
            it.toString() == UNION ||
            it.toString() == INTERCCION ||
            it.toString() == DIFERENCIA ||
            it.toString() == SUB_SET ||
            it.toString() == COMPLEMENT
        }.toList()
    }


    override fun simpleIter(iterable: String): List<String>
    {
        return iterable.map { it.toString() }
    }


    /*Esta función se prueba llamando la función rawPattern()*/
    override fun getParticularExpression(list: List<Char>, indexDivide: Int): String
    {
        println("<------------------------------->")
        var c = ""
        list.forEachIndexed { index, item ->
            if (index < indexDivide) {
                c += item
            }
        }
        println("List Arrived: $list")
        println("Chars Cut: $c")
        println("Cut Factor: $indexDivide")
        println("<------------------------------->")
        return c
    }


    /*Uso fold, esto me permite recolectar resultados y
    * reducirlos a un solo valor.
    * Funciona con un Acumulador: acc, y un elemento en este caso entity
    * su elemento inicial es un emptySet()
    * */
    override fun createUniversalSet(set: List<SetsEntity>): Set<Any>
    {
        return set.fold(emptySet()) { acc, entity ->
            union(acc, entity.set.filter { it != "" }.toSet())
        }
    }


    override fun createKeyName(list: List<SetsEntity>): String
    {
        val used = list.map { it.keyName }
        val available = Utils.getOnlyUpperLetters().filterNot { it.toString() in  used }
        return if (available.isNotEmpty()) { available.random().toString()
        } else { throw IllegalStateException("No more Chars!") }
    }


    override fun deleteLast(list: List<SetsEntity>) : List<SetsEntity>
    {
        return list.toMutableList().dropLast(1)
    }


    /*
    * Función Importante de esta aplicación
    * Funcionamiento: Obtengo los caracteres especiales,
    * luego obtengo la expresión iterada.
    *
    * KeyWords: Calcular, Guardar, Eliminar valores antiguos
    *
    * Problemas dectados:
    * 1. Cuando hay dos UNION o uno al inicio y otro al final
    * del mismo valor de texto de cualquiera se genera un problema | Solucionado!
    * */
    override fun rawPattern(expression: String, entities: List<SetsEntity>): List<SetsIterSaver>
    {
        val quantitySymbols = getQuantityOperations(expression)
        println("Quantity Special Symbols: $quantitySymbols")

        val listSpecialChars = getSpecialCharacters(expression)
        println("List Special Chars: $listSpecialChars")

        var iteredExpression = getDecodeExpression(expression)
        println("List main: $iteredExpression")

        val listToSaveLazily = mutableListOf<SetsIterSaver>()

        val listToChechValues = mutableListOf<SetsIterSaver>() /*Tiende a ser Retirada*/

        var finalSet = SetsIterSaver()

        listSpecialChars.forEachIndexed { _, _ ->
            iteredExpression.forEachIndexed { _, _ ->
                val cuteFactor = if (iteredExpression.size % 2 == 0) 2 else 3
                if (iteredExpression.isNotEmpty()) {
                    listToSaveLazily.add(
                        SetsIterSaver(
                            oldExpression = getParticularExpression(iteredExpression, cuteFactor),
                            resultSet =  setOf()
                        )
                    )
                    iteredExpression = iteredExpression.drop(cuteFactor)
                }
            }
        }

        listToSaveLazily.forEachIndexed { index, item ->
            println("index: $index")

            if (index == 0) {
                val iterated = simpleIter(item.oldExpression)
                println("iterated: $iterated")

                val first = entities.filter { it.keyName == iterated.first() }[0]
                val second = entities.filter { it.keyName == iterated.last() }[0]

                finalSet = when {
                    item.oldExpression.contains(UNION) ->
                        SetsIterSaver(
                            oldExpression = item.oldExpression,
                            resultSet = union(setOf(), setOf())
                        )

                    item.oldExpression.contains(INTERCCION) ->
                        SetsIterSaver(
                            oldExpression = item.oldExpression,
                            resultSet = intersect(setOf(), setOf())
                        )

                    item.oldExpression.contains(SUB_SET) ->
                        SetsIterSaver(
                            oldExpression = item.oldExpression,
                            resultSet = subSet(setOf(), setOf())
                        )

                    item.oldExpression.contains(DIFERENCIA) ->
                        SetsIterSaver(
                            oldExpression = item.oldExpression,
                            resultSet = difference(first.set, second.set)
                        )

                    /*
                    * Complemento tendra un tratamiento diferente
                    * */

                    else -> SetsIterSaver()
                }

                listToChechValues.add(finalSet)

            } else {

                val iterated = simpleIter(item.oldExpression)
                val oldSet = finalSet.resultSet
                val nextExpression = entities.filter { it.keyName == iterated.last() }[0]

                finalSet = when {
                    iterated.contains(UNION) ->
                        SetsIterSaver(
                            oldExpression = finalSet.oldExpression + item.oldExpression,
                            resultSet = union(oldSet, nextExpression.set)
                        )

                    iterated.contains(INTERCCION) ->
                        SetsIterSaver(
                            oldExpression = finalSet.oldExpression + item.oldExpression,
                            resultSet = intersect(oldSet, nextExpression.set)
                        )

                    iterated.contains(SUB_SET) ->
                        SetsIterSaver(
                            oldExpression = finalSet.oldExpression + item.oldExpression,
                            resultSet = subSet(oldSet, nextExpression.set)
                        )

                    iterated.contains(DIFERENCIA) ->
                        SetsIterSaver(
                            oldExpression = finalSet.oldExpression + item.oldExpression,
                            resultSet = difference(oldSet, nextExpression.set)
                        )

                    /*
                    * Complemento tendra un tratamiento diferente
                    * */

                    else -> SetsIterSaver()
                }

                println(item.oldExpression)
                listToChechValues.add(finalSet)
            }
        }

        println("\n<------------- General ------------->\n$listToSaveLazily")
        println("\n<------------- Checker ------------->\n$listToChechValues")
        println("\n<------------- Final Set ------------->\n$finalSet\n")
        return listToChechValues.toList()
    }


    override fun splitSetsSimplex(expression: String, sets: List<SetsEntity>): SetsResultsEntity
    {
        val setsEntityL = mutableListOf<SetsEntity>()
        val resultSets = setOf<SetsResultsEntity>()

        val symbol = expression.filter {
            it.toString() == UNION || it.toString() == INTERCCION ||
                    it.toString() == DIFERENCIA || it.toString() == SUB_SET ||
                    it.toString() == COMPLEMENT
        }

        expression.forEach { item ->
            sets.forEach { sItem ->
                if (sItem.keyName.contains(item)) {
                    setsEntityL.add(
                        sItem.copy(
                            set = sItem.set.filter { it != "" }.toSet()
                        )
                    )
                }
            }
        }

        val confirm = setsEntityL.size > 1
        val universal = createUniversalSet(setsEntityL)

        return when (symbol) {
            UNION ->
                SetsResultsEntity(
                    expression = expression,
                    resultSet = if (confirm) union(setsEntityL[0].set, setsEntityL[1].set)
                    else union(emptySet(), emptySet())
                )

            INTERCCION ->
                SetsResultsEntity(
                    expression = expression,
                    resultSet = if (confirm) intersect(setsEntityL[0].set, setsEntityL[1].set)
                    else union(emptySet(), emptySet())
                )

            DIFERENCIA ->
                SetsResultsEntity(
                    expression = expression,
                    resultSet = if (confirm) difference(setsEntityL[0].set, setsEntityL[1].set)
                    else union(emptySet(), emptySet())
                )

            SUB_SET ->
                SetsResultsEntity(
                    expression = expression,
                    resultSet = if (confirm) subSet(setsEntityL[0].set, setsEntityL[1].set)
                    else union(emptySet(), emptySet())
                )

            COMPLEMENT ->
                SetsResultsEntity(
                    expression = expression,
                    resultSet = if (confirm) complement(universal, setsEntityL[1].set)
                    else union(emptySet(), emptySet())
                )
            else -> SetsResultsEntity()
        }
    }

}