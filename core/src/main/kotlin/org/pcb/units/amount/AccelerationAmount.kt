package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.div
import org.pcb.units.domain.Acceleration
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class AccelerationAmount<S : Unit<Space>, T1 : Unit<Time>, T2 : Unit<Time>>(
        amount: Number,
        val space: S,
        val time1: T1,
        val time2: T2)
    : Amount<Acceleration>(amount = amount.toBigBigDecimal(),
                           unit = CompositeUnit(
                               domain = Acceleration::class,
                               units = listOf(space to 1,
                                              time1 to -1,
                                              time2 to -1)))
{
    // region Acceleration

    operator fun plus(that: AccelerationAmount<*, *, *>) =
        AccelerationAmount(amount = amount + that.amount * convert(that.space to space,
                                                                   that.time1 to time1,
                                                                   that.time2 to time2),
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("plusST1T2")
    operator fun plus(that: AccelerationAmount<S, T1, T2>) =
        AccelerationAmount(amount = amount + that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("plusST2T1")
    operator fun plus(that: AccelerationAmount<S, T2, T1>) =
        AccelerationAmount(amount = amount + that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    operator fun minus(that: AccelerationAmount<*, *, *>) =
        AccelerationAmount(amount = amount - that.amount * convert(that.space to space,
                                                                   that.time1 to time1,
                                                                   that.time2 to time2),
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("minusST1T2")
    operator fun minus(that: AccelerationAmount<S, T1, T2>) =
        AccelerationAmount(amount = amount - that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("minusST2T1")
    operator fun minus(that: AccelerationAmount<S, T2, T1>) =
        AccelerationAmount(amount = amount - that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    // endregion

    // region Scalar

    operator fun div(that: AccelerationAmount<*, *, *>) =
        ScalarAmount(amount = amount / that.amount / convert(that.space to space,
                                                             that.time1 to time1,
                                                             that.time2 to time2))

    @JvmName("divST1T2")
    operator fun div(that: AccelerationAmount<S, T1, T2>) =
        ScalarAmount(amount = amount / that.amount)

    @JvmName("divST2T1")
    operator fun div(that: AccelerationAmount<S, T2, T1>) =
        ScalarAmount(amount = amount / that.amount)

    // endregion

    // region Speed

    operator fun times(that: TimeAmount<*>) =
        SpeedAmount(amount = amount * that.amount * convert(that.time to time2),
                    space = space,
                    time = time1)

    @JvmName("timesT1")
    operator fun times(that: TimeAmount<T1>) =
        SpeedAmount(amount = amount * that.amount,
                    space = space,
                    time = time2)

    @JvmName("timesT2")
    operator fun times(that: TimeAmount<T2>) =
        SpeedAmount(amount = amount * that.amount,
                    space = space,
                    time = time1)

    // endregion

    // region Frequency

    operator fun times(that: SpeedAmount<*, *>) =
        FrequencyAmount(amount = amount * that.amount * convert(that.space to space,
                                                                that.time to time2),
                        time = time1)

    @JvmName("timesST1")
    operator fun times(that: SpeedAmount<S, T1>) =
        FrequencyAmount(amount = amount * that.amount,
                        time = time2)

    @JvmName("timesST2")
    operator fun times(that: SpeedAmount<S, T2>) =
        FrequencyAmount(amount = amount * that.amount,
                        time = time1)

    // endregion

    // region Force

    operator fun times(that: MassAmount<*>) =
        ForceAmount(amount = amount * that.amount,
                    mass = that.mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    // endregion
}