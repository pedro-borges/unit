package org.pcb.unit.units

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.convert
import org.pcb.unit.dimension.Distance
import org.pcb.unit.dimension.ElectricCurrent
import org.pcb.unit.dimension.Force
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
import org.pcb.unit.units.scalar.Numeric

open class PowerAmount<M : Unit<Mass>, D2 : Unit<Distance>, T3 : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, D2>, T3>)
    : Amount<Force>(amount = amount.toBigBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = listOf(unit.major.first to 1,
                                       unit.major.second to 2,
                                       unit.minor to -3)))
{
    val mass = unit.major.first
    val distance = unit.major.second
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
                    unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / that.unit.scale,
                    unit = U2(mass, distance) to time)

    @JvmName("plusPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> plus(that: PowerAmount<M, D, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun plus(that: PowerAmount<M, D2, T3>) =
        PowerAmount(amount = amount + that.amount,
                    unit = U2(mass, distance) to time)

    @JvmName("minusPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> minus(that: PowerAmount<M, D, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun minus(that: PowerAmount<M, D2, T3>) =
        PowerAmount(amount = amount - that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Natural

    @JvmName("divPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: PowerAmount<M, D, T>) =
        Numeric(amount = amount / that.amount
                              / convert(that.mass to mass,
                                        that.distance to distance,
                                        that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time,
                                        that.time to time))

    operator fun div(that: PowerAmount<M, D2, T3>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Energy

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = U2(mass, distance) to time)

    operator fun times(that: TimeAmount<T3>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, distance) to time)

    // endregion

    // region -> Force

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.distance to distance)
                             * convert(that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun div(that: SpeedAmount<D2, T3>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Speed

    @JvmName("divForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: ForceAmount<M, D, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass,
                                       that.distance to distance)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = distance to time)

    operator fun div(that: ForceAmount<M, D2, T3>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, D, T, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<M, D2, T3, EC>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(mass, distance) to U2(time, that.electricCurrent))

    // endregion
}