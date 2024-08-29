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

    operator fun plus(that: SpeedAmount<*, *>) =
        SpeedAmount(amount = amount + that.amount * convert(that.space to space,
                                                            time to that.time),
                    space = space,
                    time = time)

    @JvmName("plusST")
    operator fun plus(that: SpeedAmount<S, T>) =
        SpeedAmount(amount = amount + that.amount,
                    space = space,
                    time = time)

    operator fun minus(that: SpeedAmount<*, *>) =
        SpeedAmount(amount = amount - that.amount * convert(that.space to space,
                                                            time to that.time),
                    space = space,
                    time = time)

    @JvmName("minusST")
    operator fun minus(that: SpeedAmount<S, T>) =
        SpeedAmount(amount = amount - that.amount,
                    space = space,
                    time = time)

    // endregion

    // region Scalar
    operator fun div(that: SpeedAmount<*, *>) =
        NaturalAmount(amount = amount / that.amount / convert(that.space to space, time to that.time))

    @JvmName("divST")
    operator fun div(that: SpeedAmount<S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Space

    operator fun times(that: TimeAmount<*>) =
        SpaceAmount(amount = amount * that.amount * convert(that.time to time),
                    space = space)

    @JvmName("timesT")
    operator fun times(that: TimeAmount<T>) =
        SpaceAmount(amount = amount * that.amount,
                    space = space)

    // endregion

    // region Acceleration

    operator fun <U : Unit<Time>> div(that: TimeAmount<U>) =
        AccelerationAmount(amount = amount / that.amount,
                           space = space,
                           time1 = time,
                           time2 = that.time)

    // endregion

    // region Frequency

    operator fun div(that: SpaceAmount<*>) =
        FrequencyAmount(amount = amount / that.amount / convert(that.space to space),
                        time = time)

    @JvmName("divS")
    operator fun div(that: SpaceAmount<S>) =
        FrequencyAmount(amount = amount / that.amount,
                        time = time)

    // endregion
}