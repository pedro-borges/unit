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
import org.pcb.unit.dimensions.EnergyDensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class EnergyDensityAmount<D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<D, T>>)
    : Amount<EnergyDensity>(amount = amount.toBigDecimal(),
                            unit = CompositeUnit(
                                domain = EnergyDensity::class,
                                units = mapOf(unit.major to 1,
                                              unit.minor.first to -1,
                                              unit.minor.second to -2)))
{
    val mass = unit.major
    val distance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<EnergyDensity>) =
            EnergyDensityAmount(amount = amount * unit.scale,
                                unit = kilo(GRAM) to U2(METER, SECOND))
    }

    // region -> EnergyDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        EnergyDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                            unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        EnergyDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                            unit = mass to U2(distance, time))

    @JvmName("plusEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount + that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance,
                                               that.time to time,
                                               that.time to time),
                            unit = mass to U2(distance, time))

    operator fun plus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount + that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("minusEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount - that.amount
                                     * convert(that.mass to mass)
                                     / convert(that.distance to distance,
                                               that.time to time,
                                               that.time to time),
                            unit = mass to U2(distance, time))

    operator fun minus(that: EnergyDensityAmount<D, M, T>) =
        EnergyDensityAmount(amount = amount - that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Scalar

    @JvmName("divEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: EnergyDensityAmount<D, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<D, M>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.mass to mass)
                                    * convert(that.distance to distance,
                                              that.distance to distance),
                           unit = distance to time)

    operator fun div(that: AreaDensityAmount<D, M>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> AreaDensity

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time),
                          unit = mass to distance)

    operator fun div(that: AccelerationAmount<D, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Energy

    @JvmName("timesVolume")
    operator fun <D : Unit<Distance>> times(that: VolumeAmount<D>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance),
                     unit = U2(distance, mass) to time)

    operator fun times(that: VolumeAmount<D>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: AreaAmount<D>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Power

    @JvmName("timesVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance)
                             / convert(that.time to time),
                    unit = U2(distance, mass) to time)

    operator fun times(that: VolumetricFlowAmount<D, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion
}
