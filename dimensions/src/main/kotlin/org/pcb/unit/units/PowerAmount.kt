package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
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
import org.pcb.unit.units.scalar.Numeric.NUMERIC

open class PowerAmount<M : Unit<Mass>, L2 : Unit<Length>, T3 : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, L2>, T3>)
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
                        unit = U2(KILOGRAM, METER) to SECOND)
    }

    // region -> Power

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * that.unit.scale,
                    unit = U2(mass, length) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / that.unit.scale,
                    unit = U2(mass, length) to time)

    @JvmName("plusPower")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> plus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(mass, length) to time)

    operator fun plus(that: PowerAmount<M, L2, T3>) =
        PowerAmount(amount = amount + that.amount,
                    unit = U2(mass, length) to time)

    @JvmName("minusPower")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> minus(that: PowerAmount<M, L, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.length to length,
                                       that.length to length)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(mass, length) to time)

    operator fun minus(that: PowerAmount<M, L2, T3>) =
        PowerAmount(amount = amount - that.amount,
                    unit = U2(mass, length) to time)

    // endregion

    // region -> Natural

    @JvmName("divPower")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: PowerAmount<M, L, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.length to length,
                                        that.length to length)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: PowerAmount<M, L2, T3>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Energy

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = U2(mass, length) to time)

    operator fun times(that: TimeAmount<T3>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, length) to time)

    // endregion

    // region -> Force

    @JvmName("divSpeed")
    operator fun <L : Unit<Length>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.length to length)
                             * convert(that.time to time),
                    unit = U2(mass, length) to time)

    operator fun div(that: SpeedAmount<L2, T3>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(mass, length) to time)

    // endregion

    // region -> Speed

    @JvmName("divForce")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>> div(that: ForceAmount<M, L, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass,
                                       that.length to length)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = length to time)

    operator fun div(that: ForceAmount<M, L2, T3>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = length to time)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, L : Unit<Length>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.length to length,
                                                 that.length to length)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, L2, T3, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(mass, length) to U2(time, that.electricCurrent))

    // endregion
}