package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Triple
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Power
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND
import org.pcb.unit.units.scalar.NaturalAmount

open class PowerAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        major: Triple<M, L, L>,
        minor: Triple<T, T, T>)
    : Amount<Force>(amount = amount.toBigBigDecimal()
                             * convert(major.third to major.second)
                             / convert(minor.second to minor.first,
                                       minor.third to minor.first),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(major.first to 1,
                                       major.second to 2,
                                       minor.first to -3)))
{
    val mass = major.first
    val length = major.second
    val time = minor.first

    companion object
    {
        operator fun invoke(amount: Number, power: Unit<Power>) =
            PowerAmount(amount = amount.toBigBigDecimal() * power.scale,
                        major = KILOGRAM * METER * METER,
                        minor = SECOND * SECOND * SECOND)
    }

    // region Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * that.unit.scale,
                    major = mass * length * length,
                    minor = time * time * time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / that.unit.scale,
                    major = mass * length * length,
                    minor = time * time * time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    major = mass * length * length,
                    minor = time * time * time)

    @JvmName("plusPower")
    operator fun plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + that.amount,
                    major = mass * length * length,
                    minor = time * time * time)


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    major = mass * length * length,
                    minor = time * time * time)

    @JvmName("minusPower")
    operator fun minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - that.amount,
                    major = mass * length * length,
                    minor = time * time * time)

    // endregion

    // region Scalar

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: PowerAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount
                               / convert(that.mass to mass,
                                         that.length to length,
                                         that.length to length)
                               * convert(that.time to time,
                                         that.time to time,
                                         that.time to time))

    @JvmName("divPower")
    operator fun div(that: PowerAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Energy

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time),
                     major = mass * length * length,
                     minor = time * time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount,
                     major = mass * length * length,
                     minor = time * time)

    // endregion

    // region ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              major = that.electricCurrent)

    @JvmName("divElectricTension")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              major = that.electricCurrent)

    // endregion

    // region ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              major = mass * length * length,
                              minor = time * time * time * that.electricCurrent)

    // endregion
}