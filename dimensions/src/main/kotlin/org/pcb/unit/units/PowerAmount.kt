package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.T2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Force
import org.pcb.unit.dimension.Length
import org.pcb.unit.dimension.Mass
import org.pcb.unit.dimension.Power
import org.pcb.unit.dimension.Scalar
import org.pcb.unit.dimension.Time
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.to
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

open class PowerAmount<M : Unit<Mass>, L : Unit<Length>, T3 : Unit<Time>>(
        amount: Number,
        unit: Pair<T2<M, L>, T3>)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.major.first to 1,
                                       unit.major.second to 2,
                                       unit.minor to -3)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Power>) =
            PowerAmount(amount = amount.toBigBigDecimal() * unit.scale,
                        unit = Pair(T2(KILOGRAM, METER), SECOND))
    }

    // region -> Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * that.unit.scale,
                    unit = Pair(T2(mass, length),
                                time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount.to(that.amount).to(that.unit.scale),
                    unit = Pair(T2(mass, length),
                                time))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + (that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                      ).to(convert(that.time to time,
                                                   that.time to time,
                                                   that.time to time)),
                    unit = Pair(T2(mass, length),
                                time))

    @JvmName("plusPower")
    operator fun plus(that: PowerAmount<M, L, T3>) =
        PowerAmount(amount = amount + that.amount,
                    unit = Pair(T2(mass, length),
                                time))


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - (that.amount
                                       * convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                      ).to(convert(that.time to time,
                                                   that.time to time,
                                                   that.time to time)),
                    unit = Pair(T2(mass, length),
                                time))

    @JvmName("minusPower")
    operator fun minus(that: PowerAmount<M, L, T3>) =
        PowerAmount(amount = amount - that.amount,
                    unit = Pair(T2(mass, length),
                                time))

    // endregion

    // region -> Natural

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: PowerAmount<M, L, T>) =
        amount.to(that.amount).to(convert(that.mass to mass,
                                          that.length to length,
                                          that.length to length)) *
        convert(that.time to time,
                that.time to time,
                that.time to time)

    @JvmName("divPower")
    operator fun div(that: PowerAmount<M, L, T3>) =
        amount.to(that.amount)

    // endregion

    // region -> Energy

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = Pair(T2(mass, length),
                                 time))

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T3>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = Pair(T2(mass, length),
                                 time))

    // endregion

    // region -> ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount.to(that.amount)
                                           .to(convert(that.mass to mass,
                                                       that.length to length,
                                                       that.length to length))
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    @JvmName("divElectricTension")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T3, EC>) =
        ElectricCurrentAmount(amount = amount.to(that.amount),
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount.to(that.amount),
                              unit = Pair(T2(mass, length),
                                          T2(time, that.electricCurrent)))

    // endregion
}