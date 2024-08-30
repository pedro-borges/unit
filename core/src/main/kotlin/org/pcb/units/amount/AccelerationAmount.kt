package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Acceleration
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.or
import org.pcb.units.toBigBigDecimal

open class AccelerationAmount<S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val space: S,
        val time1: T,
        val time2: T)
    : Amount<Acceleration>(amount = amount.toBigBigDecimal(),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = listOf(space to 1,
                                              time1 to -1,
                                              time2 to -1)))
{
    // region Acceleration

    operator fun <S : Unit<Space>, T : Unit<Time>> plus(that: AccelerationAmount<S, T>) =
        AccelerationAmount(amount = amount + that.amount * convert(that.space to space,
                                                                   time1 to that.time1,
                                                                   time2 to that.time2),
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("plusAcceleration")
    operator fun plus(that: AccelerationAmount<S, T>) =
        AccelerationAmount(amount = amount + that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    operator fun <S : Unit<Space>, T : Unit<Time>> minus(that: AccelerationAmount<S, T>) =
        AccelerationAmount(amount = amount - that.amount * convert(that.space to space,
                                                                   time1 to that.time1,
                                                                   time2 to that.time2),
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("minusAcceleration")
    operator fun minus(that: AccelerationAmount<S, T>) =
        AccelerationAmount(amount = amount - that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    // endregion

    // region Scalar

    operator fun <S : Unit<Space>, T : Unit<Time>> div(that: AccelerationAmount<S, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.space to space,
                                                              time1 to that.time1,
                                                              time2 to that.time2))

    @JvmName("divAcceleration")
    operator fun div(that: AccelerationAmount<S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount * convert(that.time to (time1 or time2)),
                    space = space,
                    time = time1 or time2)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        SpeedAmount(amount = amount * that.amount,
                    space = space,
                    time = time1 or time2)

    // endregion

    // region Frequency

    operator fun <S : Unit<Space>, T : Unit<Time>> div(that: SpeedAmount<S, T>) =
        FrequencyAmount(amount = amount / that.amount * convert(space to that.space,
                                                                that.time to (time1 or time2)),
                        time = time1 or time2)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<S, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        time = time1 or time2)

    // endregion

    // region Force

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        ForceAmount(amount = amount * that.amount,
                    mass = that.mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    // endregion
}