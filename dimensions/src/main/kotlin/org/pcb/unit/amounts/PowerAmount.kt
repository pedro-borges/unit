package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Power
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PowerAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, T>)
    : Amount<Power>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Power::class,
                        units = mapOf(unit.major.first to 2,
                                      unit.major.second to 1,
                                      unit.minor to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Power>) =
            PowerAmount(amount = amount * unit.scale,
                        unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Power

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    @JvmName("plusPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> plus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun plus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount + that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("minusPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> minus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun minus(that: PowerAmount<D, M, T>) =
        PowerAmount(amount = amount - that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Numeric

    @JvmName("divPower")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: PowerAmount<D, M, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance,
                                   that.distance to distance)
                         * convert(that.time to time,
                                   that.time to time,
                                   that.time to time))

    operator fun div(that: PowerAmount<D, M, T>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Energy

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyAmount(amount = amount * that.amount * convert(that.time to time),
                     unit = U2(mass, distance) to time)

    operator fun times(that: TimeAmount<T>) =
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

    operator fun div(that: SpeedAmount<D, T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Speed

    @JvmName("divForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: ForceAmount<D, M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass,
                                       that.distance to distance)
                             * convert(that.time to time,
                                       that.time to time),
                    unit = distance to time)

    operator fun div(that: ForceAmount<D, M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divElectricTension")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>, EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.mass to mass,
                                                 that.distance to distance,
                                                 that.distance to distance)
                                       * convert(that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.electricCurrent)

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricTensionAmount<D, EC, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = that.electricCurrent)

    // endregion

    // region -> ElectricTension

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<EC>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(mass, distance) to U2(time, that.electricCurrent))

    // endregion
}