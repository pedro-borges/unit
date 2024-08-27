package org.pcb.units.unit

import java.math.BigDecimal
import java.math.BigDecimal.ONE
import kotlin.reflect.KClass
import org.pcb.units.display.Renderer
import org.pcb.units.domain.Domain

open class CompositeUnit<D : Domain>(val units: Map<Unit<*>, Int>, domain: KClass<D>)
    : Unit<D>(domain)
{
    constructor(unit: Unit<D>, domain: KClass<D>) : this(mapOf(unit to 1), domain)

    override val symbol = Renderer.render(units)
    override val cannon = units.entries.fold(ONE) { acc, entry -> acc * (entry.key.cannon.power(entry.value)) }

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

    private fun MutableMap<Unit<*>, Int>.putOrRemove(unit: Unit<*>, value: Int)
    {
        if (value == 0) this.remove(unit)
        else this.put(unit, value)
    }

    private fun BigDecimal.power(power: Int): BigDecimal
    {
        var result = ONE
        for (a in 1..kotlin.math.abs(power))
        {
            if (power > 0) result *= this
            if (power < 0) result /= this
        }
        return result
    }
}