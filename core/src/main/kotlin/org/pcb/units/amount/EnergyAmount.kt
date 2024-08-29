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

open class EnergyAmount<M : Unit<Mass>, S1 : Unit<Space>, S2 : Unit<Space>, T1 : Unit<Time>, T2 : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space1: S1,
        val space2: S2,
        val time1: T1,
        val time2: T2)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       space1 to 1,
                                       space2 to 1,
                                       time1 to -1,
                                       time2 to -1)))
{
    // region Energy

    operator fun plus(that: EnergyAmount<*, *, *, *, *>) =
        EnergyAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                             that.space1 to space1,
                                                             that.space2 to space2,
                                                             that.time1 to time1,
                                                             that.time2 to time2),
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("plusMS1S2T1T2")
    operator fun plus(that: EnergyAmount<M, S1, S2, T1, T2>) =
        EnergyAmount(amount = amount + that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("plusMS1S2T2T1")
    operator fun plus(that: EnergyAmount<M, S1, S2, T2, T1>) =
        EnergyAmount(amount = amount + that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("plusMS2S1T1T2")
    operator fun plus(that: EnergyAmount<M, S2, S1, T1, T2>) =
        EnergyAmount(amount = amount + that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("plusMS2S1T2T1")
    operator fun plus(that: EnergyAmount<M, S2, S1, T2, T1>) =
        EnergyAmount(amount = amount + that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    operator fun minus(that: EnergyAmount<*, *, *, *, *>) =
        EnergyAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                             that.space1 to space1,
                                                             that.space2 to space2,
                                                             that.time1 to time1,
                                                             that.time2 to time2),
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("minusMS1S2T1T2")
    operator fun minus(that: EnergyAmount<M, S1, S2, T1, T2>) =
        EnergyAmount(amount = amount - that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("minusMS1S2T2T1")
    operator fun minus(that: EnergyAmount<M, S1, S2, T2, T1>) =
        EnergyAmount(amount = amount - that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("minusMS2S1T1T2")
    operator fun minus(that: EnergyAmount<M, S2, S1, T1, T2>) =
        EnergyAmount(amount = amount - that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("minusMS2S1T2T1")
    operator fun minus(that: EnergyAmount<M, S2, S1, T2, T1>) =
        EnergyAmount(amount = amount - that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    // endregion

    // region Scalar

    operator fun div(that: EnergyAmount<*, *, *, *, *>) =
        ScalarAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                             that.space1 to space1,
                                                             that.space2 to space2,
                                                             that.time1 to time1,
                                                             that.time2 to time2))

    @JvmName("divMS1S2T1T2")
    operator fun div(that: EnergyAmount<M, S1, S2, T1, T2>) =
        ScalarAmount(amount = amount / that.amount)

    @JvmName("divMS1S2T2T1")
    operator fun div(that: EnergyAmount<M, S1, S2, T2, T1>) =
        ScalarAmount(amount = amount / that.amount)

    @JvmName("divMS2S1T1T2")
    operator fun div(that: EnergyAmount<M, S2, S1, T1, T2>) =
        ScalarAmount(amount = amount / that.amount)

    @JvmName("divMS2S1T2T1")
    operator fun div(that: EnergyAmount<M, S2, S1, T2, T1>) =
        ScalarAmount(amount = amount / that.amount)

    // endregion

    // region Force

    operator fun div(that: SpaceAmount<*>) =
        ForceAmount(amount = amount / that.amount / convert(that.space to space2),
                    mass = mass,
                    space = space1,
                    time1 = time1,
                    time2 = time2)

    @JvmName("divS1")
    operator fun div(that: SpaceAmount<S1>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    space = space2,
                    time1 = time1,
                    time2 = time2)

    @JvmName("divS2")
    operator fun div(that: SpaceAmount<S2>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    space = space1,
                    time1 = time1,
                    time2 = time2)

    // endregion

    // region Space

    operator fun div(that: ForceAmount<*, *, *, *>) =
        SpaceAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                            that.space to space2,
                                                            that.time1 to time1,
                                                            that.time2 to time2),
                    space = space1)

    @JvmName("divMS1T1T2")
    operator fun div(that: ForceAmount<M, S1, T1, T2>) =
        SpaceAmount(amount = amount / that.amount,
                    space = space2)

    @JvmName("divMS1T2T1")
    operator fun div(that: ForceAmount<M, S1, T2, T1>) =
        SpaceAmount(amount = amount / that.amount,
                    space = space2)

    @JvmName("divMS2T1T2")
    operator fun div(that: ForceAmount<M, S2, T1, T2>) =
        SpaceAmount(amount = amount / that.amount,
                    space = space1)

    @JvmName("divMS2T2T1")
    operator fun div(that: ForceAmount<M, S2, T2, T1>) =
        SpaceAmount(amount = amount / that.amount,
                    space = space1)

    // endregion
}