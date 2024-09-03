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
        val mass: M,
        val length: L,
        length2: L,
        val time: T,
        time2: T,
        time3: T)
    : Amount<Force>(amount = amount.toBigBigDecimal() * convert(length2 to length,
                                                                time to time2,
                                                                time to time3),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(mass to 1,
                                       length to 2,
                                       time to -3)))
{
    constructor(amount: Number, pair: Pair<Triple<M, L, L>, Triple<T, T, T>>) : this(amount, pair.first.first, pair.first.second, pair.first.third, pair.second.first, pair.second.second, pair.second.third)

    companion object
    {
        operator fun invoke(amount: Number, power: Unit<Power>) =
            PowerAmount(amount = amount.toBigBigDecimal() * power.scale,
                        mass = KILOGRAM,
                        length = METER,
                        length2 = METER,
                        time = SECOND,
                        time2 = SECOND,
                        time3 = SECOND)
    }

    // region Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * that.unit.scale,
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / that.unit.scale,
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    @JvmName("plusPower")
    operator fun plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + that.amount,
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

    @JvmName("minusPower")
    operator fun minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - that.amount,
                    mass = mass,
                    length = length,
                    length2 = length,
                    time = time,
                    time2 = time,
                    time3 = time)

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