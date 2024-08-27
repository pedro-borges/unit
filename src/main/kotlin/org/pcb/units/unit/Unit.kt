package org.pcb.units.unit

import java.math.BigDecimal
import kotlin.reflect.KClass
import org.pcb.units.domain.Domain

abstract class Unit<D : Domain>(val domain: KClass<D>)
{
    abstract val symbol: String
    abstract val cannon: BigDecimal

    override fun equals(other: Any?): Boolean
    {
        return when
        {
            this === other   -> true
            other is Unit<*> -> symbol == other.symbol &&
                                cannon == other.cannon
            else             -> false
        }
    }

    override fun hashCode(): Int
    {
        var result = symbol.hashCode()
        result = 31 * result + cannon.hashCode()
        return result
    }
}