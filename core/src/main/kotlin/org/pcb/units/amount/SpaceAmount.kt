package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Scalar
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

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpaceAmount(amount = amount * that.amount * that.unit.scale,
                    space = space)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpaceAmount(amount = amount / that.amount / that.unit.scale,
                    space = space)

    operator fun <S : Unit<Space>> plus(that: SpaceAmount<S>) =
        SpaceAmount(amount = amount + that.amount * convert(that.space to space),
                    space = space)

    @JvmName("plusSpace")
    operator fun plus(that: SpaceAmount<S>) =
        SpaceAmount(amount = amount + that.amount,
                    space = space)

    operator fun <S : Unit<Space>> minus(that: SpaceAmount<S>) =
        SpaceAmount(amount = amount - that.amount * convert(that.space to space),
                    space = space)

    @JvmName("minusSpace")
    operator fun minus(that: SpaceAmount<S>) =
        SpaceAmount(amount = amount - that.amount,
                    space = space)

    // endregion

    // region Scalar

    operator fun <S : Unit<Space>> div(that: SpaceAmount<S>) =
        NaturalAmount(amount = amount / that.amount / convert(that.space to space))

    @JvmName("divSpace")
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