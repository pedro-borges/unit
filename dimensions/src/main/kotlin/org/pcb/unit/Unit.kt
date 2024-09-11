package org.pcb.unit

import java.math.BigDecimal
import java.util.Objects
import kotlin.reflect.KClass
import org.pcb.unit.dimension.Dimension

abstract class Unit<D : Dimension>(val domain: KClass<D>, val symbol: String, val magnitude: Magnitude, val scale: BigDecimal)
{
    override fun equals(other: Any?) =
        when
        {
            this === other   -> true
            other is Unit<*> -> symbol == other.symbol &&
                                scale * magnitude.scale == other.scale * other.magnitude.scale
            else             -> false
        }

    override fun hashCode() =
        Objects.hash(symbol, scale * magnitude.scale)
}