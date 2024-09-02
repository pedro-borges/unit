package org.pcb.units.amount

import org.pcb.units.Amount
import org.pcb.units.CompositeUnit
import org.pcb.units.Unit
import org.pcb.units.amount.base.Kilogram.KILOGRAM
import org.pcb.units.amount.base.Meter.METER
import org.pcb.units.amount.base.Second.SECOND
import org.pcb.units.amount.scalar.NaturalAmount
import org.pcb.units.convert
import org.pcb.units.dimension.ElectricCurrent
import org.pcb.units.dimension.Force
import org.pcb.units.dimension.Length
import org.pcb.units.dimension.Mass
import org.pcb.units.dimension.Power
import org.pcb.units.dimension.Scalar
import org.pcb.units.dimension.Time
import org.pcb.units.div
import org.pcb.units.times
import org.pcb.units.toBigBigDecimal

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
        PowerAmount(amount = amount + that.amount * convert(that.mass to mass,
                                                            that.length to length,
                                                            that.length to length,
                                                            time to that.time,
                                                            time to that.time,
                                                            time to that.time),
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
        PowerAmount(amount = amount - that.amount * convert(that.mass to mass,
                                                            that.length to length,
                                                            that.length to length,
                                                            time to that.time,
                                                            time to that.time,
                                                            time to that.time),
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
        NaturalAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                              that.length to length,
                                                              that.length to length,
                                                              time to that.time,
                                                              time to that.time,
                                                              time to that.time))

    @JvmName("divPower")
    operator fun div(that: PowerAmount<M, L, T>) =
        NaturalAmount(amount = amount / that.amount)

    // endregion

    // region Energy

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount * convert(time to that.time),
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount,
                     mass = mass,
                     length = length,
                     length2 = length,
                     time = time,
                     time2 = time)

    // endregion

    // region ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount / convert(that.mass to mass,
                                                                      that.length to length,
                                                                      that.length to length,
                                                                      time to that.time,
                                                                      time to that.time,
                                                                      time to that.time),
                              electricCurrent = that.electricCurrent)

    @JvmName("divElectricTension")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              electricCurrent = that.electricCurrent)

    // endregion

    // region ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
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