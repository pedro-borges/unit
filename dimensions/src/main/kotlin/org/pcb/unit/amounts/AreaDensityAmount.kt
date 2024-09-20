package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.AreaDensity
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class AreaDensityAmount<D : Unit<Distance>, M : Unit<Mass>>(
        amount: Number,
        unit: Pair<M, D>)
    : Amount<AreaDensity>(amount = amount.toBigDecimal(),
                          unit = CompositeUnit(
                              domain = AreaDensity::class,
                              units = mapOf(unit.major to 1,
                                            unit.minor to -2)))
{
    val mass = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<AreaDensity>) =
            AreaDensityAmount(amount = amount * unit.scale,
                              unit = kilo(GRAM) to METER)
    }

    // region -> AreaDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        AreaDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        AreaDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                          unit = mass to distance)

    @JvmName("plusAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> plus(that: AreaDensityAmount<D, M>) =
        AreaDensityAmount(amount = amount + that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun plus(that: AreaDensityAmount<D, M>) =
        AreaDensityAmount(amount = amount + that.amount,
                          unit = mass to distance)

    @JvmName("minusAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> minus(that: AreaDensityAmount<D, M>) =
        AreaDensityAmount(amount = amount - that.amount
                                   * convert(that.mass to mass)
                                   / convert(that.distance to distance,
                                             that.distance to distance),
                          unit = mass to distance)

    operator fun minus(that: AreaDensityAmount<D, M>) =
        AreaDensityAmount(amount = amount - that.amount,
                          unit = mass to distance)

    // endregion

    // region -> Scalar

    @JvmName("divAreaDensity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<D, M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: AreaDensityAmount<D, M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Distance

    @JvmName("timesSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        DistanceAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance)
                                / convert(that.mass to mass),
                       unit = distance)

    operator fun times(that: SpecificVolumeAmount<D, M>) =
        DistanceAmount(amount = amount * that.amount,
                       unit = distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesAcceleration")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance),
                            unit = mass to U2(distance, that.time))

    operator fun <T : Unit<Time>> times(that: AccelerationAmount<D, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, that.time))

    // endregion

    // region -> Mass

    @JvmName("timesArea")
    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = mass)

    operator fun times(that: AreaAmount<D>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> Momentum

    @JvmName("timesVolumetricFlow")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance),
                       unit = U2(distance, mass) to that.time)

    operator fun <T : Unit<Time>> times(that: VolumetricFlowAmount<D, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, mass) to that.time)

    // endregion
}
