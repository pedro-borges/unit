package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Space
import org.pcb.units.domain.Speed
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class SpeedAmount<S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val space: S,
        val time: T)
    : Amount<Speed>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Speed::class,
                        units = listOf(space to 1,
                                       time to -1)))
{
    // region Speed

    operator fun <S : Unit<Space>, T : Unit<Time>> plus(that: SpeedAmount<S, T>) =
        SpeedAmount(amount = amount + that.amount * convert(that.space to space,
                                                            time to that.time),
                    space = space,
                    time = time)

    @JvmName("plusSpeed")
    operator fun plus(that: SpeedAmount<S, T>) =
        SpeedAmount(amount = amount + that.amount,
                    space = space,
                    time = time)

    operator fun <S : Unit<Space>, T : Unit<Time>> minus(that: SpeedAmount<S, T>) =
        SpeedAmount(amount = amount - that.amount * convert(that.space to space,
                                                            time to that.time),
                    space = space,
                    time = time)

    @JvmName("minusSpeed")
    operator fun minus(that: SpeedAmount<S, T>) =
        SpeedAmount(amount = amount - that.amount,
                    space = space,
                    time = time)

    // endregion

    // region Scalar
    operator fun <S : Unit<Space>, T : Unit<Time>> div(that: SpeedAmount<S, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.space to space, time to that.time))

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Space

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SpaceAmount(amount = amount * that.amount * convert(that.time to time),
                    space = space)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        SpaceAmount(amount = amount * that.amount,
                    space = space)

    // endregion

    // region Acceleration

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.time to time),
                           space = space,
                           time = time)

    // endregion

    // region Frequency

    operator fun <S : Unit<Space>> div(that: SpaceAmount<S>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.space to space),
                        time = time)

    @JvmName("divSpace")
    operator fun div(that: SpaceAmount<S>) =
        FrequencyAmount(amount = amount / that.amount,
                        time = time)

    // endregion
}