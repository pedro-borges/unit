package org.pcb.unit

import java.math.BigDecimal
import java.util.Objects
import kotlin.reflect.KClass
import org.pcb.unit.config.Configuration
import org.pcb.unit.dimension.Dimension

abstract class Unit<D : Dimension>(
        val domain: KClass<D>,
        val symbol: String,
        val scale: BigDecimal,
        val magnitude: Magnitude)
{
    override fun equals(other: Any?) =
        when
        {
            this === other   -> true
            other is Unit<*> -> symbol == other.symbol &&
                                scale.movePointRight(magnitude.power) == other.scale.movePointRight(other.magnitude.power)
            else             -> false
        }

    override fun hashCode() =
        Objects.hash(symbol, scale.movePointRight(magnitude.power))

    override fun toString() =
        Configuration.renderer.render(this)
}