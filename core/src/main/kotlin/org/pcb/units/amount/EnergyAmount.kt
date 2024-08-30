package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.div
import org.pcb.units.domain.Force
import org.pcb.units.domain.Length
import org.pcb.units.domain.Mass
import org.pcb.units.domain.Scalar
import org.pcb.units.domain.Time
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

open class EnergyAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        val mass: M,
        val length: L,
        length2: L,
        val time: T,
        time2: T)
    : Amount<Force>(amount = amount.toBigBigDecimal() * convert(length2 to length,
                                                                time to time2),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       length to 2,
                                       time to -2)))
{
    // region Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount * that.amount * that.unit.scale,
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount / that.amount / that.unit.scale,
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                             that.length to length,
                                                             that.length to length,
                                                             time to that.time,
                                                             time to that.time),
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    @JvmName("plusEnergy")
    operator fun plus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount + that.amount,
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                             that.length to length,
                                                             that.length to length,
                                                             time to that.time,
                                                             time to that.time),
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    @JvmName("minusEnergy")
    operator fun minus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount - that.amount,
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: EnergyAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.length to length,
                                                              that.length to length,
                                                              time to that.time,
                                                              time to that.time))

    @JvmName("divEnergy")
    operator fun div(that: EnergyAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Force

    operator fun <L : Unit<Length>> div(that: LengthAmount<L>) =
        ForceAmount(amount = amount / that.amount / convert(that.length to length),
                    mass = mass,
                    length = length,
                    time = time,
                    time2 = time)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        ForceAmount(amount = amount / that.amount,
                    mass = mass,
                    length = length,
                    time = time,
                    time2 = time)

    // endregion

    // region Length

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                             that.length to length,
                                                             time to that.time,
                                                             time to that.time),
                     length = length)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount,
                     length = length)

    // endregion
}