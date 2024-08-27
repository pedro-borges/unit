package org.pcb.units.unit

import java.math.BigDecimal.ONE
import java.math.MathContext.DECIMAL128
import kotlin.reflect.KClass
import org.pcb.units.display.Renderer
import org.pcb.units.domain.Domain
import org.pcb.units.power
import org.pcb.units.putOrRemove

open class CompositeUnit<D : Domain>(val units: Map<Unit<*>, Int>, domain: KClass<D>)
    : Unit<D>(domain)
{
    constructor(unit: Unit<D>, domain: KClass<D>) : this(mapOf(unit to 1), domain)

    override val symbol = Renderer.render(units)
    override val cannon
        get() =
            units.entries
                .fold(ONE) { acc, entry ->
                    acc.multiply(entry.key.cannon.power(entry.value), DECIMAL128)
                }

    operator fun times(unit: Unit<*>) =
        CompositeUnit(units.toMutableMap()
                          .also { it.putOrRemove(unit, (it[unit] ?: 0) + 1) },
                      Domain::class)

    operator fun div(unit: Unit<*>) =
        CompositeUnit(units.toMutableMap()
                          .also { it.putOrRemove(unit, (it[unit] ?: 0) - 1) },
                      Domain::class)

    operator fun times(composite: CompositeUnit<*>) =
        CompositeUnit(units.toMutableMap()
                          .also {
                              composite.units.forEach { that ->
                                  it.putOrRemove(that.key,
                                                 (it[that.key] ?: 0) + that.value)
                              }
                          },
                      Domain::class)

    operator fun div(composite: CompositeUnit<*>) =
        CompositeUnit(units.toMutableMap()
                          .also {
                              composite.units.forEach { that ->
                                  it.putOrRemove(that.key, (it[that.key] ?: 0) - that.value)
                              }
                          },
                      Domain::class)

    override fun toString() = symbol
}