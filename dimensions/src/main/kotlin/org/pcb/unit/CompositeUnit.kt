package org.pcb.unit

import java.util.Objects
import kotlin.reflect.KClass
import org.pcb.unit.dimension.Domain

open class CompositeUnit<D : Domain>(domain: KClass<D>,
                                     units: Collection<Pair<Unit<*>, Int>>)
    : Unit<D>(domain)
{
    val units: Map<Unit<*>, Int> = units.sumToMap()

    override val symbol
        get() =
            Renderer.render(this.units)
    override val scale
        get() =
            units.entries
                .fold(1.toBigDecimal()) { acc, entry ->
                    acc * entry.key.scale.power(entry.value)
                }

    override fun <B : Unit<*>> times(second: B): Tuple<Unit<D>, B> = throw UnsupportedOperationException()

    operator fun times(unit: Unit<*>) =
        CompositeUnit(Domain::class,
                      units.toMutableMap()
                          .also { it.putOrRemove(unit, (it[unit] ?: 0) + 1) }
                          .entries
                          .map { Pair(it.key, it.value) })

    operator fun div(unit: Unit<*>) =
        CompositeUnit(Domain::class,
                      units.toMutableMap()
                          .also { it.putOrRemove(unit, (it[unit] ?: 0) - 1) }
                          .entries
                          .map { Pair(it.key, it.value) })

    operator fun times(composite: CompositeUnit<*>) =
        CompositeUnit(Domain::class,
                      units.toMutableMap()
                          .also {
                              composite.units.forEach { that ->
                                  it.putOrRemove(that.key,
                                                 (it[that.key] ?: 0) + that.value)
                              }
                          }
                          .entries
                          .map { Pair(it.key, it.value) })

    operator fun div(composite: CompositeUnit<*>) =
        CompositeUnit(Domain::class,
                      units.toMutableMap()
                          .also {
                              composite.units.forEach { that ->
                                  it.putOrRemove(that.key, (it[that.key] ?: 0) - that.value)
                              }
                          }
                          .entries
                          .map { Pair(it.key, it.value) })

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