package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.div
import org.pcb.units.domain.Force
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.toBigBigDecimal

open class ForceAmount<M : Unit<Mass>, S : Unit<Space>, T1 : Unit<Time>, T2 : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space: S,
        val time1: T1,
        val time2: T2)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       space to 1,
                                       time1 to -1,
                                       time2 to -1)))
{
    // region Force

    operator fun plus(that: ForceAmount<*, *, *, *>) =
        ForceAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                            that.space to space,
                                                            that.time1 to time1,
                                                            that.time2 to time2),
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    @JvmName("plusMST1T2")
    operator fun plus(that: ForceAmount<M, S, T1, T2>) =
        ForceAmount(amount = amount + that.amount,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    @JvmName("plusMST2T1")
    operator fun plus(that: ForceAmount<M, S, T2, T1>) =
        ForceAmount(amount = amount + that.amount,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    operator fun minus(that: ForceAmount<*, *, *, *>) =
        ForceAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                            that.space to space,
                                                            that.time1 to time1,
                                                            that.time2 to time2),
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    @JvmName("minusMST1T2")
    operator fun minus(that: ForceAmount<M, S, T1, T2>) =
        ForceAmount(amount = amount - that.amount,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    @JvmName("minusMST2T1")
    operator fun minus(that: ForceAmount<M, S, T2, T1>) =
        ForceAmount(amount = amount - that.amount,
                    mass = mass,
                    space = space,
                    time1 = time1,
                    time2 = time2)

    // endregion

    // region Scalar

    operator fun div(that: ForceAmount<*, *, *, *>) =
        ScalarAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                             that.space to space,
                                                             that.time1 to time1,
                                                             that.time2 to time2))

    @JvmName("divMST1T2")
    operator fun div(that: ForceAmount<M, S, T1, T2>) =
        ScalarAmount(amount = amount / that.amount)

    @JvmName("divMST2T1")
    operator fun div(that: ForceAmount<M, S, T2, T1>) =
        ScalarAmount(amount = amount / that.amount)

    // endregion

    // region Acceleration

    operator fun div(that: MassAmount<*>) =
        AccelerationAmount(amount = amount / that.amount / convert(that.mass to mass),
                           space = space,
                           time1 = time1,
                           time2 = time2)

    @JvmName("divM")
    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           space = space,
                           time1 = time1,
                           time2 = time2)

    // endregion

    // region Mass

    operator fun div(that: AccelerationAmount<*, *, *>) =
        MassAmount(amount = amount / that.amount / convert(that.space to space,
                                                           that.time1 to time1,
                                                           that.time2 to time2),
                   mass = mass)

    @JvmName("divST1T2")
    operator fun div(that: AccelerationAmount<S, T1, T2>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    @JvmName("divST2T1")
    operator fun div(that: AccelerationAmount<S, T2, T1>) =
        MassAmount(amount = amount / that.amount,
                   mass = mass)

    // endregion

    // region Momentum

    operator fun times(that: TimeAmount<*>) =
        MomentumAmount(amount = amount * that.amount * convert(that.time to time2),
                       mass = mass,
                       space = space,
                       time = time1)

    @JvmName("timesT1")
    operator fun times(that: TimeAmount<T1>) =
        MomentumAmount(amount = amount * that.amount,
                       mass = mass,
                       space = space,
                       time = time2)

    @JvmName("timesT2")
    operator fun times(that: TimeAmount<T2>) =
        MomentumAmount(amount = amount * that.amount,
                       mass = mass,
                       space = space,
                       time = time1)

    // endregion

    // region Energy

    operator fun times(that: SpaceAmount<*>) =
        EnergyAmount(amount = amount * that.amount,
                     mass = mass,
                     space1 = space,
                     space2 = that.space,
                     time1 = time1,
                     time2 = time2)

    // endregion
}