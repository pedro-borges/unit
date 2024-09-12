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
import org.pcb.unit.dimensions.Force
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ForceAmount<M : Unit<Mass>, D : Unit<Distance>, T2 : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<M, D>, T2>)
    : Amount<Force>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = mapOf(unit.major.first to 1,
                                      unit.major.second to 1,
                                      unit.minor to -2)))
{
    val mass = unit.major.first
    val distance = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Force>) =
            ForceAmount(amount = amount * unit.scale,
                        unit = U2(kilo(GRAM), METER) to SECOND)
    }

    // region -> Force

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ForceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = U2(mass, distance) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ForceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = U2(mass, distance) to time)

    @JvmName("plusForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> plus(that: ForceAmount<M, D, T>) =
        ForceAmount(amount = amount + that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun plus(that: ForceAmount<M, D, T2>) =
        ForceAmount(amount = amount + that.amount,
                    unit = U2(mass, distance) to time)

    @JvmName("minusForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> minus(that: ForceAmount<M, D, T>) =
        ForceAmount(amount = amount - that.amount
                             * convert(that.mass to mass,
                                       that.distance to distance)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun minus(that: ForceAmount<M, D, T2>) =
        ForceAmount(amount = amount - that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Natural

    @JvmName("divForce")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: ForceAmount<M, D, T>) =
        Numeric(amount = amount / that.amount
                         / convert(that.mass to mass,
                                   that.distance to distance)
                         * convert(that.time to time,
                                   that.time to time))

    operator fun div(that: ForceAmount<M, D, T2>) =
        Numeric(amount = amount / that.amount)

    // endregion

    // region -> Acceleration

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount / that.amount / convert(that.mass to mass),
                                                unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        org.pcb.unit.amounts.AccelerationAmount(amount = amount / that.amount,
                                                unit = distance to time)

    // endregion

    // region -> Mass

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: org.pcb.unit.amounts.AccelerationAmount<D, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: org.pcb.unit.amounts.AccelerationAmount<D, T2>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> Momentum

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount * convert(that.time to time),
                       unit = U2(mass, distance) to time)

    operator fun times(that: TimeAmount<T2>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(mass, distance) to time)

    // endregion

    // region -> Energy

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        EnergyAmount(amount = amount * that.amount * convert(that.distance to distance),
                     unit = U2(mass, distance) to time)

    operator fun times(that: DistanceAmount<D>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(mass, distance) to time)

    // endregion

    // region -> Power

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = U2(mass, distance) to time)

    operator fun times(that: SpeedAmount<D, T2>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(mass, distance) to time)

    // endregion

    // region -> Area

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        PressureAmount(amount = amount / that.amount / convert(that.distance to distance,
                                                               that.distance to distance),
                       unit = mass to U2(distance, time))

    operator fun div(that: AreaAmount<D>) =
        PressureAmount(amount = amount / that.amount,
                       unit = mass to U2(distance, time))

    // endregion

    // region -> Pressure

    @JvmName("divPressure")
    operator fun <M : Unit<Mass>, D : Unit<Distance>, T : Unit<Time>> div(that: PressureAmount<M, D, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: PressureAmount<M, D, T2>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion
}