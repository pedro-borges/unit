package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.SpecificEnergy
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class SpecificEnergyAmount<D : Unit<Distance>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<D, T>)
    : Amount<SpecificEnergy>(amount = amount.toBigDecimal(),
                             unit = CompositeUnit(
                                 domain = SpecificEnergy::class,
                                 units = mapOf(unit.major to 2,
                                               unit.minor to -2)))
{
    val distance = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<SpecificEnergy>) =
            SpecificEnergyAmount(amount = amount * unit.scale,
                                 unit = METER to SECOND)
    }

    // region -> SpecificEnergy

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        SpecificEnergyAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                             unit = distance to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        SpecificEnergyAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                             unit = distance to time)

    @JvmName("plusSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> plus(that: SpecificEnergyAmount<D, T>) =
        SpecificEnergyAmount(amount = amount + that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun plus(that: SpecificEnergyAmount<D, T>) =
        SpecificEnergyAmount(amount = amount + that.amount,
                             unit = distance to time)

    @JvmName("minusSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> minus(that: SpecificEnergyAmount<D, T>) =
        SpecificEnergyAmount(amount = amount - that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance)
                                      / convert(that.time to time,
                                                that.time to time),
                             unit = distance to time)

    operator fun minus(that: SpecificEnergyAmount<D, T>) =
        SpecificEnergyAmount(amount = amount - that.amount,
                             unit = distance to time)

    // endregion

    // region -> Scalar

    @JvmName("divSpecificEnergy")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpecificEnergyAmount<D, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance)
                              * convert(that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: SpecificEnergyAmount<D, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("divDistance")
    operator fun <D : Unit<Distance>> div(that: DistanceAmount<D>) =
        AccelerationAmount(amount = amount / that.amount
                                    / convert(that.distance to distance),
                           unit = distance to time)

    operator fun div(that: DistanceAmount<D>) =
        AccelerationAmount(amount = amount / that.amount,
                           unit = distance to time)

    // endregion

    // region -> Distance

    @JvmName("divAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: AccelerationAmount<D, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.distance to distance)
                                * convert(that.time to time,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: AccelerationAmount<D, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> Energy

    operator fun <M : Unit<Mass>> times(that: MassAmount<M>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("divSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     / convert(that.distance to distance,
                                               that.distance to distance,
                                               that.distance to distance),
                            unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> div(that: SpecificVolumeAmount<D, M>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = that.mass to U2(distance, time))

    // endregion

    // region -> Power

    @JvmName("timesMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MassFlowRateAmount<M, T>) =
        PowerAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: MassFlowRateAmount<M, T>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to time)

    // endregion

    // region -> SpecificVolume

    @JvmName("divEnergyDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<D, M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount
                                      * convert(that.distance to distance,
                                                that.time to time,
                                                that.time to time),
                             unit = distance to that.mass)

    operator fun <M : Unit<Mass>> div(that: EnergyDensityAmount<D, M, T>) =
        SpecificVolumeAmount(amount = amount / that.amount,
                             unit = distance to that.mass)

    // endregion

    // region -> Speed

    @JvmName("timesPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<D, T>) =
        SpeedAmount(amount = amount * that.amount
                             * convert(that.time to time)
                             / convert(that.distance to distance),
                    unit = distance to time)

    operator fun times(that: PaceAmount<D, T>) =
        SpeedAmount(amount = amount * that.amount,
                    unit = distance to time)

    @JvmName("divSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<D, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance)
                             * convert(that.time to time),
                    unit = distance to time)

    operator fun div(that: SpeedAmount<D, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion
}
