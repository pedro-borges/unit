package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Pace
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class PaceAmount<T : Unit<Time>, S : Unit<Space>>(
        amount: Number,
        val time: T,
        val space: S)
    : Amount<Pace>(amount = amount.toBigBigDecimal(),
                   unit = CompositeUnit(
                       domain = Pace::class,
                       units = listOf(time to 1,
                                      space to -1)))
{
    // region Pace

    operator fun <T : Unit<Time>, S : Unit<Space>> plus(that: PaceAmount<T, S>) =
        PaceAmount(amount = amount + that.amount * convert(that.time to time,
                                                           space to that.space),
                   time = time,
                   space = space)

    @JvmName("plusPace")
    operator fun plus(that: PaceAmount<T, S>) =
        PaceAmount(amount = amount + that.amount,
                   time = time,
                   space = space)

    operator fun <T : Unit<Time>, S : Unit<Space>> minus(that: PaceAmount<T, S>) =
        PaceAmount(amount = amount - that.amount * convert(that.time to time,
                                                           space to that.space),
                   time = time,
                   space = space)

    @JvmName("minusPace")
    operator fun minus(that: PaceAmount<T, S>) =
        PaceAmount(amount = amount - that.amount,
                   time = time,
                   space = space)

    // endregion

    // region Scalar

    operator fun <T : Unit<Time>, S : Unit<Space>> div(that: PaceAmount<T, S>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time,
                                                              space to that.space))

    @JvmName("divPace")
    operator fun div(that: PaceAmount<T, S>) =
        NaturalAmount(amount = amount / that.amount)

    operator fun <S : Unit<Space>, T : Unit<Time>> times(that: SpeedAmount<S, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.time to time,
                                                              space to that.space))

    @JvmName("timesSpeed")
    operator fun times(that: SpeedAmount<S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Time

    operator fun <T : Unit<Space>> times(that: SpaceAmount<T>) =
        TimeAmount(amount = amount * that.amount * convert(that.space to space),
                   time = time)

    @JvmName("timesSpace")
    operator fun times(that: SpaceAmount<S>) =
        TimeAmount(amount = amount * that.amount,
                   time = time)

    // endregion
}