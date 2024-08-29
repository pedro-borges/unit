package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class SpaceAmount<S : Unit<Space>>(
        amount: Number,
        val space: S)
    : Amount<Space>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Space::class,
                        units = listOf(space to 1)))
{
    // region Space

    operator fun plus(that: SpaceAmount<*>) =
        SpaceAmount(amount = amount + that.amount * convert(that.space to space),
                    space = space)

    @JvmName("plusS")
    operator fun plus(that: SpaceAmount<S>) =
        SpaceAmount(amount = amount + that.amount,
                    space = space)

    operator fun minus(that: SpaceAmount<*>) =
        SpaceAmount(amount = amount - that.amount * convert(that.space to space),
                    space = space)

    @JvmName("minusS")
    operator fun minus(that: SpaceAmount<S>) =
        SpaceAmount(amount = amount - that.amount,
                    space = space)

    // endregion

    // region Scalar

    operator fun div(that: SpaceAmount<*>) =
        NaturalAmount(amount = amount / that.amount / convert(that.space to space))

    @JvmName("divS")
    operator fun div(that: SpaceAmount<S>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        SpeedAmount(amount = amount / that.amount,
                    space = space,
                    time = that.time)

    // endregion
}