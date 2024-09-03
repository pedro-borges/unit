package org.pcb.unit

import java.math.BigDecimal
import kotlin.reflect.KClass
import org.pcb.unit.dimension.Domain

abstract class Unit<D : Domain>(val domain: KClass<out D>)
{
    abstract val symbol: String
    abstract val scale: BigDecimal

    abstract operator fun <B : Unit<*>> times(second: B): Tuple<Unit<D>, B>
    abstract operator fun <B : Unit<*>> div(second: B): Pair<Unit<D>, B>
    abstract operator fun <B : Tuple<*, *>> div(second: B): Pair<Unit<D>, B>
    abstract operator fun <B : Triple<*, *, *>> div(second: B): Pair<Unit<D>, B>
    abstract operator fun <B : Quadruple<*, *, *, *>> div(second: B): Pair<Unit<D>, B>
    abstract operator fun <B : Quintuple<*, *, *, *, *>> div(second: B): Pair<Unit<D>, B>

    override fun equals(other: Any?): Boolean
    {
        return when
        {
            this === other   -> true
            other is Unit<*> -> symbol == other.symbol &&
                                scale == other.scale
            else             -> false
        }
    }

    override fun hashCode(): Int
    {
        var result = symbol.hashCode()
        result = 31 * result + scale.hashCode()
        return result
    }
}