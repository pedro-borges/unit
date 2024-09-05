package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.Fraction
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
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigBigDecimal
import org.pcb.unit.units.base.Kilogram.KILOGRAM
import org.pcb.unit.units.base.Meter.METER
import org.pcb.unit.units.base.Second.SECOND

open class PowerAmount<M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>>(
        amount: Number,
        unit: Fraction<Triple<M, L, L>, Triple<T, T, T>>)
    : Amount<Force>(amount = amount.toBigBigDecimal()
                             * convert(unit.major.third to unit.major.second)
                             / convert(unit.minor.second to unit.minor.first,
                                       unit.minor.third to unit.minor.first),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.major.first to 1,
                                       unit.major.second to 2,
                                       unit.minor.first to -3)))
{
    val mass = unit.major.first
    val length = unit.major.second
    val time = unit.minor.first

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Power>) =
            PowerAmount(amount = amount.toBigBigDecimal() * unit.scale,
                        unit = (KILOGRAM * METER * METER) /
                               (SECOND * SECOND * SECOND))
    }

    // region -> Energy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * that.unit.scale,
                    unit = (mass * length * length) /
                           (time * time * time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / that.unit.scale,
                    unit = (mass * length * length) /
                           (time * time * time))

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = (mass * length * length) /
                           (time * time * time))

    @JvmName("plusPower")
    operator fun plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + that.amount,
                    unit = (mass * length * length) /
                           (time * time * time))


    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = (mass * length * length) /
                           (time * time * time))

    @JvmName("minusPower")
    operator fun minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - that.amount,
                    unit = (mass * length * length) /
                           (time * time * time))

    // endregion

    // region -> Natural

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: PowerAmount<M, L, T>) =
        amount / that.amount /
        convert(that.mass to mass,
                that.length to length,
                that.length to length) *
        convert(that.time to time,
                that.time to time,
                that.time to time)

    @JvmName("divPower")
    operator fun div(that: PowerAmount<M, L, T>) =
        amount / that.amount

    // endregion

    // region -> Energy

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = (mass * length * length) /
                            (time * time))

    @JvmName("timesTime")
    operator fun times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = (mass * length * length) /
                            (time * time))

    // endregion

    // region -> ElectricCurrent

    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    @JvmName("divElectricTension")
    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = (mass * length * length) /
                                     (time * time * time * that.electricCurrent))

    // endregion
}