package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
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

open class ForceAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<D, M>, T>)
    : Amount<Force>(amount = amount.toBigDecimal(),
                    unit = CompositeUnit(
                        domain = Force::class,
                        units = mapOf(unit.major.first to 1,
                                      unit.major.second to 1,
                                      unit.minor to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<Force>) =
            ForceAmount(amount = amount * unit.scale,
                        unit = U2(METER, kilo(GRAM)) to SECOND)
    }

    // region -> Force

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ForceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ForceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                    unit = U2(distance, mass) to time)

    @JvmName("plusForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: ForceAmount<D, M, T>) =
        ForceAmount(amount = amount + that.amount
                             * convert(that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun plus(that: ForceAmount<D, M, T>) =
        ForceAmount(amount = amount + that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("minusForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: ForceAmount<D, M, T>) =
        ForceAmount(amount = amount - that.amount
                             * convert(that.distance to distance,
                                       that.mass to mass)
                             / convert(that.time to time,
                                       that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun minus(that: ForceAmount<D, M, T>) =
        ForceAmount(amount = amount - that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Scalar

    @JvmName("divForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ForceAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass),
                           unit = distance to time)

    operator fun div(that: MassAmount<M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Area

    @JvmName("divEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<D, M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: EnergyDensityAmount<D, M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> Energy

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: DistanceAmount<D>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("divArea")
    operator fun <D : Unit<Distance>> div(that: AreaAmount<D>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance),
                            unit = mass to U2(distance, time))

    operator fun div(that: AreaAmount<D>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Frequency

    @JvmName("divMomentum")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<D, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.mass to mass)
                                 * convert(that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: MomentumAmount<D, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> Mass

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        MassAmount(amount = amount / that.amount
                            / convert(that.distance to distance)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = mass)

    operator fun div(that: AccelerationAmount<D, T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    // endregion

    // region -> MassFlowRate

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.time to time)
                                    / convert(that.distance to distance),
                           unit = mass to time)

    operator fun times(that: PaceAmount<D, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        MassFlowRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance)
                                    * convert(that.time to time),
                           unit = mass to time)

    operator fun div(that: SpeedAmount<D, T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to time)

    // endregion

    // region -> Momentum

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MomentumAmount(amount = amount / that.amount
                                * convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun div(that: FrequencyAmount<T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(distance, mass) to time)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.time to time),
                       unit = U2(distance, mass) to time)

    operator fun times(that: TimeAmount<T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divPower")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun div(that: PowerAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Power

    @JvmName("divPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        PowerAmount(amount = amount / that.amount
                             / convert(that.time to time)
                             * convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun div(that: PaceAmount<D, T>) =
        PowerAmount(amount = amount / that.amount,
                    unit = U2(distance, mass) to time)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance)
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: SpeedAmount<D, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Speed

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.mass to mass)
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("divYank")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: YankAmount<D, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion

    // region -> Yank

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        YankAmount(amount = amount * that.amount
                            / convert(that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun times(that: FrequencyAmount<T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        YankAmount(amount = amount / that.amount
                            / convert(that.time to time),
                   unit = U2(distance, mass) to time)

    operator fun div(that: TimeAmount<T>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
