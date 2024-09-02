package org.pcb.unit.amount

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Unit
import org.pcb.unit.amount.base.Kilogram.KILOGRAM
import org.pcb.unit.amount.base.Meter.METER
import org.pcb.unit.amount.base.Second.SECOND
import org.pcb.unit.amount.scalar.NaturalAmount
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Energy
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal

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
    companion object
    {
        operator fun invoke(amount: Number, energy: Unit<Energy>) =
            EnergyAmount(amount = amount.toBigBigDecimal() * energy.scale,
                         mass = KILOGRAM,
                         length = METER,
                         length2 = METER,
                         time = SECOND,
                         time2 = SECOND)
    }

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
        EnergyAmount(amount = amount + that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
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
        EnergyAmount(amount = amount - that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
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
        NaturalAmount(amount = amount / that.amount
                               / convert(that.mass to mass,
                                         that.length to length,
                                         that.length to length)
                               * convert(that.time to time,
                                         that.time to time))

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
        LengthAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time),
                     length = length)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount,
                     length = length)

    // endregion

    // region Power

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount / convert(that.time to time),
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount,
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    // endregion

    // region ElectricTension

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = that.electricCurrent)

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              mass = mass,
                              length = length,
                              length2 = length,
                              time = time,
                              time2 = time,
                              time3 = time,
                              electricCurrent = that.electricCurrent)

    // endregion
}