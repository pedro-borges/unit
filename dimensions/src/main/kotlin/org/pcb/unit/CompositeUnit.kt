package org.pcb.unit

import java.util.Objects
import kotlin.reflect.KClass
import org.pcb.unit.config.Configuration
import org.pcb.unit.dimensions.Dimension

open class CompositeUnit<D : Dimension>(domain: KClass<D>,
                                        val units: Map<Unit<*>, Int>)
    : Unit<D>(domain,
              Configuration.renderer.render(units),
              units.entries.fold(1.toBigDecimal()) { acc, entry -> acc * entry.key.scale.power(entry.value) },
              Magnitude.get(units.entries.map { it.key.magnitude.power * it.value }.fold(0) { acc, power -> acc + power }))
{
    fun times(unit: Unit<*>) =
        CompositeUnit(Dimension::class,
                      units.toMutableMap()
                          .also { it.putOrRemove(unit, (it[unit] ?: 0) + 1) })

    operator fun div(unit: Unit<*>) =
        CompositeUnit(Dimension::class,
                      units.toMutableMap()
                          .also { it.putOrRemove(unit, (it[unit] ?: 0) - 1) })

    operator fun times(composite: CompositeUnit<*>) =
        CompositeUnit(Dimension::class,
                      units.toMutableMap()
                          .also {
                              composite.units.forEach { that ->
                                  it.putOrRemove(that.key,
                                                 (it[that.key] ?: 0) + that.value)
                              }
                          })

    operator fun div(composite: CompositeUnit<*>) =
        CompositeUnit(Dimension::class,
                      units.toMutableMap()
                          .also {
                              composite.units.forEach { that ->
                                  it.putOrRemove(that.key, (it[that.key] ?: 0) - that.value)
                              }
                          })

    override fun equals(other: Any?): Boolean
    {
        return when
        {
            this === other            -> true
            other is CompositeUnit<*> -> units == other.units
            else                      -> false
        }
    }

    override fun hashCode(): Int
    {
        return Objects.hash(units)
    }

    override fun toString() = symbol
}