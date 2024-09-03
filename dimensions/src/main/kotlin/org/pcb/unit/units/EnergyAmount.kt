package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Triple
import org.pcb.unit.Tuple
import org.pcb.unit.Unit
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
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class EnergyAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        major: Triple<M, L, L>,
        minor: Tuple<T, T>)
    : Amount<Force>(amount = amount.toBigBigDecimal()
                             * convert(major.third to major.second)
                             / convert(minor.second to minor.first),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(major.first to 1,
                                       major.second to 2,
                                       minor.first to -2)))
{
    val mass = major.first
    val length = major.second
    val time = minor.first

    companion object
    {
        operator fun invoke(amount: Number, energy: Unit<Energy>) =
            EnergyAmount(amount = amount.toBigBigDecimal() * energy.scale,
                         major = KILOGRAM * METER * METER,
                         minor = SECOND * SECOND)
    }

    // region Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount * that.amount * that.unit.scale,
                     major = mass * length * length,
                     minor = time * time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyAmount(amount = amount / that.amount / that.unit.scale,
                     major = mass * length * length,
                     minor = time * time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount + that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
                     major = mass * length * length,
                     minor = time * time)

    @JvmName("plusEnergy")
    operator fun plus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount + that.amount,
                     major = mass * length * length,
                     minor = time * time)


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount - that.amount
                              * convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              / convert(that.time to time,
                                        that.time to time),
                     major = mass * length * length,
                     minor = time * time)

    @JvmName("minusEnergy")
    operator fun minus(that: EnergyAmount<M, L, T>) =
        EnergyAmount(amount = amount - that.amount,
                     major = mass * length * length,
                     minor = time * time)

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
                    major = mass * length,
                    minor = time * time)

    @JvmName("divLength")
    operator fun div(that: LengthAmount<L>) =
        ForceAmount(amount = amount / that.amount,
                    major = mass * length,
                    minor = time * time)

    // endregion

    // region Length

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time),
                     major = length)

    @JvmName("divForce")
    operator fun div(that: ForceAmount<M, L, T>) =
        LengthAmount(amount = amount / that.amount,
                     major = length)

    // endregion

    // region Speed

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: MomentumAmount<M, L, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass,
                                       that.length to length)
                             * convert(that.time to time),
                    length = length,
                    time = time)

    @JvmName("divMomentum")
    operator fun div(that: MomentumAmount<M, L, T>) =
        SpeedAmount(amount = amount / that.amount,
                    length = length,
                    time = time)

    // endregion

    // region Momentum

    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount
                                / convert(that.length to length)
                                * convert(that.time to time),
                       major = mass * length,
                       minor = time)

    @JvmName("divSpeed")
    operator fun div(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount,
                       major = mass * length,
                       minor = time)

    // endregion

    // region Power

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount / convert(that.time to time),
                    major = mass * length * length,
                    minor = time * time * time)

    @JvmName("divTime")
    operator fun div(that: TimeAmount<T>) =
        PowerAmount(amount = amount / that.amount,
                    major = mass * length * length,
                    minor = time * time * time)

    // endregion

    // region ElectricTension

    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount / convert(that.time to time),
                              major = mass * length * length,
                              minor = time * time * time * that.electricCurrent)

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<EC, T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              major = mass * length * length,
                              minor = time * time * time * that.electricCurrent)

    // endregion
}