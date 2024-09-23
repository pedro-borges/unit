package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Kelvin.KELVIN
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.TemperatureGradient
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class TemperatureGradientAmount<D : Unit<Distance>, K : Unit<Temperature>>(
        amount: Number,
        unit: Pair<K, D>)
    : Amount<TemperatureGradient>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = TemperatureGradient::class,
                                      units = mapOf(unit.major to 1,
                                                    unit.minor to -1)))
{
    val temperature = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<TemperatureGradient>) =
            TemperatureGradientAmount(amount = amount * unit.scale,
                                      unit = KELVIN to METER)
    }

    // region -> TemperatureGradient

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        TemperatureGradientAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = temperature to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        TemperatureGradientAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = temperature to distance)

    @JvmName("plusTemperatureGradient")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>> plus(that: TemperatureGradientAmount<D, K>) =
        TemperatureGradientAmount(amount = amount + that.amount
                                           * convert(that.temperature to temperature)
                                           / convert(that.distance to distance),
                                  unit = temperature to distance)

    operator fun plus(that: TemperatureGradientAmount<D, K>) =
        TemperatureGradientAmount(amount = amount + that.amount,
                                  unit = temperature to distance)

    @JvmName("minusTemperatureGradient")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>> minus(that: TemperatureGradientAmount<D, K>) =
        TemperatureGradientAmount(amount = amount - that.amount
                                           * convert(that.temperature to temperature)
                                           / convert(that.distance to distance),
                                  unit = temperature to distance)

    operator fun minus(that: TemperatureGradientAmount<D, K>) =
        TemperatureGradientAmount(amount = amount - that.amount,
                                  unit = temperature to distance)

    // endregion

    // region -> Scalar

    @JvmName("divTemperatureGradient")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>> div(that: TemperatureGradientAmount<D, K>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.temperature to temperature)
                              * convert(that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: TemperatureGradientAmount<D, K>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Acceleration

    @JvmName("timesSpecificHeatCapacity")
    operator fun <D : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> times(that: SpecificHeatCapacityAmount<D, K, T>) =
        AccelerationAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance)
                                    / convert(that.temperature to temperature),
                           unit = distance to that.time)

    operator fun <T : Unit<Time>> times(that: SpecificHeatCapacityAmount<D, K, T>) =
        AccelerationAmount(amount = amount * that.amount,
                           unit = distance to that.time)

    // endregion

    // region -> EnergyFluxDensity

    @JvmName("timesThermalConductivity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: ThermalConductivityAmount<D, M, K, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance)
                                         / convert(that.temperature to temperature),
                                unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ThermalConductivityAmount<D, M, K, T>) =
        EnergyFluxDensityAmount(amount = amount * that.amount,
                                unit = that.mass to that.time)

    // endregion

    // region -> Force

    @JvmName("timesHeatCapacity")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: HeatCapacityAmount<D, M, K, T>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance)
                             / convert(that.temperature to temperature),
                    unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: HeatCapacityAmount<D, M, K, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divTemperature")
    operator fun <K : Unit<Temperature>> div(that: TemperatureAmount<K>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.temperature to temperature),
                               unit = NUMERIC to distance)

    operator fun div(that: TemperatureAmount<K>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    @JvmName("timesThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> times(that: ThermalExpansionCoefficientAmount<K>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        / convert(that.temperature to temperature),
                               unit = NUMERIC to distance)

    operator fun times(that: ThermalExpansionCoefficientAmount<K>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Temperature

    @JvmName("timesDistance")
    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        TemperatureAmount(amount = amount * that.amount
                                   * convert(that.distance to distance),
                          unit = temperature)

    operator fun times(that: DistanceAmount<D>) =
        TemperatureAmount(amount = amount * that.amount,
                          unit = temperature)

    @JvmName("divSpacialFrequency")
    operator fun <D : Unit<Distance>> div(that: SpacialFrequencyAmount<D>) =
        TemperatureAmount(amount = amount / that.amount
                                   * convert(that.distance to distance),
                          unit = temperature)

    operator fun div(that: SpacialFrequencyAmount<D>) =
        TemperatureAmount(amount = amount / that.amount,
                          unit = temperature)

    // endregion
}
