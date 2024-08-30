package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.div
import org.pcb.units.domain.Force
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Space
import org.pcb.units.domain.Time
import org.pcb.units.or
import org.pcb.units.toBigBigDecimal

open class EnergyAmount<M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val space1: S,
        val space2: S,
        val time1: T,
        val time2: T)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       space1 to 1,
                                       space2 to 1,
                                       time1 to -1,
                                       time2 to -1),))
{
    // region Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount * that.amount * that.unit.scale,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount / that.amount / that.unit.scale,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> plus(that: EnergyAmount<M, S, T>) =
        EnergyAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                             that.space1 to space1,
                                                             that.space2 to space2,
                                                             time1 to that.time1,
                                                             time2 to that.time2),
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("plusEnergy")
    operator fun plus(that: EnergyAmount<M, S, T>) =
        EnergyAmount(amount = amount + that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)


    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> minus(that: EnergyAmount<M, S, T>) =
        EnergyAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                             that.space1 to space1,
                                                             that.space2 to space2,
                                                             time1 to that.time1,
                                                             time2 to that.time2),
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    @JvmName("minusEnergy")
    operator fun minus(that: EnergyAmount<M, S, T>) =
        EnergyAmount(amount = amount - that.amount,
                     mass = mass,
                     space1 = space1,
                     space2 = space2,
                     time1 = time1,
                     time2 = time2)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> div(that: EnergyAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.space1 to space1,
                                                              that.space2 to space2,
                                                              time1 to that.time1,
                                                              time2 to that.time2))

    @JvmName("divEnergy")
    operator fun div(that: EnergyAmount<M, S, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Force

    operator fun <S : Unit<Space>> div(that: SpaceAmount<S>) =
        ForceAmount(amount = amount / that.amount / convert(that.space to (space1 or space2)),
                    mass = mass,
                    space = space1 or space2,
                    time1 = time1,
                    time2 = time2)

    @JvmName("divSpace")
    operator fun div(that: SpaceAmount<S>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    space = space1 or space2,
                    time1 = time1,
                    time2 = time2)

    // endregion

    // region Space

    operator fun <M : Unit<Mass>, S : Unit<Space>, T : Unit<Time>> div(that: ForceAmount<M, S, T>) =
        SpaceAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                            that.space to (space1 or space2),
                                                            time1 to that.time1,
                                                            time2 to that.time2),
                    space = space1 or space2)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, S, T>) =
        SpaceAmount(amount = amount / that.amount,
                    space = space1 or space2)

    // endregion
}