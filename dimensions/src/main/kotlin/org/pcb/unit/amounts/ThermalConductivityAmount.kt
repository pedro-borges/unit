package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Kelvin.KELVIN
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.ThermalConductivity
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ThermalConductivityAmount<L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, U2<K, T>>)
    : Amount<ThermalConductivity>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = ThermalConductivity::class,
                                      units = mapOf(unit.major.first to 1,
                                                    unit.major.second to 1,
                                                    unit.minor.first to -1,
                                                    unit.minor.second to -3)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val temperature = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ThermalConductivity>) =
            ThermalConductivityAmount(amount = amount * unit.scale,
                                      unit = U2(METER, kilo(GRAM)) to U2(KELVIN, SECOND))
    }

    // region -> Convert

    fun convertTo(unit: Unit<ThermalConductivity>) =
        ThermalConductivityAmount(amount = amount * convert(this.unit to unit),
                                  unit = unit)

    // endregion

    // region -> ThermalConductivity

    override operator fun times(that: Number) =
        ThermalConductivityAmount(amount = amount * that.toBigDecimal(),
                                  unit = U2(distance, mass) to U2(temperature, time))

    override operator fun div(that: Number) =
        ThermalConductivityAmount(amount = amount / that.toBigDecimal(),
                                  unit = U2(distance, mass) to U2(temperature, time))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ThermalConductivityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = U2(distance, mass) to U2(temperature, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ThermalConductivityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = U2(distance, mass) to U2(temperature, time))

    @JvmName("plusThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> plus(that: ThermalConductivityAmount<L, M, K, T>) =
        ThermalConductivityAmount(amount = amount + that.amount
                                           * convert(that.distance to distance,
                                                     that.mass to mass)
                                           / convert(that.temperature to temperature,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = U2(distance, mass) to U2(temperature, time))

    operator fun plus(that: ThermalConductivityAmount<L, M, K, T>) =
        ThermalConductivityAmount(amount = amount + that.amount,
                                  unit = U2(distance, mass) to U2(temperature, time))

    @JvmName("minusThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> minus(that: ThermalConductivityAmount<L, M, K, T>) =
        ThermalConductivityAmount(amount = amount - that.amount
                                           * convert(that.distance to distance,
                                                     that.mass to mass)
                                           / convert(that.temperature to temperature,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = U2(distance, mass) to U2(temperature, time))

    operator fun minus(that: ThermalConductivityAmount<L, M, K, T>) =
        ThermalConductivityAmount(amount = amount - that.amount,
                                  unit = U2(distance, mass) to U2(temperature, time))

    // endregion

    // region -> Scalar

    @JvmName("divThermalConductivity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> div(that: ThermalConductivityAmount<L, M, K, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.mass to mass)
                              * convert(that.temperature to temperature,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ThermalConductivityAmount<L, M, K, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> HeatCapacity

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        HeatCapacityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(temperature, time))

    operator fun times(that: AbsementAmount<L, T>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(distance, mass) to U2(temperature, time))

    // endregion

    // region -> Intensity

    @JvmName("timesTemperatureGradient")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>> times(that: TemperatureGradientAmount<L, K>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.temperature to temperature)
                                 / convert(that.distance to distance),
                        unit = mass to time)

    operator fun times(that: TemperatureGradientAmount<L, K>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to time)

    // endregion

    // region -> SpecificHeatCapacity

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount
                                            / convert(that.mass to mass)
                                            * convert(that.distance to distance,
                                                      that.time to time),
                                   unit = distance to U2(temperature, time))

    operator fun div(that: ViscosityAmount<L, M, T>) =
        SpecificHeatCapacityAmount(amount = amount / that.amount,
                                   unit = distance to U2(temperature, time))

    // endregion

    // region -> ThermalExpansionCoefficient

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount
                                                   / convert(that.distance to distance,
                                                             that.mass to mass)
                                                   * convert(that.time to time,
                                                             that.time to time,
                                                             that.time to time),
                                          unit = NUMERIC to temperature)

    operator fun div(that: YankAmount<L, M, T>) =
        ThermalExpansionCoefficientAmount(amount = amount / that.amount,
                                          unit = NUMERIC to temperature)

    // endregion

    // region -> Viscosity

    @JvmName("divSpecificHeatCapacity")
    operator fun <L : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> div(that: SpecificHeatCapacityAmount<L, K, T>) =
        ViscosityAmount(amount = amount / that.amount
                                 / convert(that.distance to distance,
                                           that.distance to distance)
                                 * convert(that.temperature to temperature,
                                           that.time to time,
                                           that.time to time),
                        unit = mass to U2(distance, time))

    operator fun div(that: SpecificHeatCapacityAmount<L, K, T>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(distance, time))

    // endregion

    // region -> Volume

    @JvmName("timesThermalResistance")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, K : Unit<Temperature>, T : Unit<Time>> times(that: ThermalResistanceAmount<L, M, K, T>) =
        VolumeAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.temperature to temperature,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time)
                              / convert(that.mass to mass),
                     unit = distance)

    operator fun times(that: ThermalResistanceAmount<L, M, K, T>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = distance)

    // endregion

    // region -> Yank

    @JvmName("timesTemperature")
    operator fun <K : Unit<Temperature>> times(that: TemperatureAmount<K>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.temperature to temperature),
                   unit = U2(distance, mass) to time)

    operator fun times(that: TemperatureAmount<K>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("divThermalExpansionCoefficient")
    operator fun <K : Unit<Temperature>> div(that: ThermalExpansionCoefficientAmount<K>) =
        YankAmount(amount = amount / that.amount
                            * convert(that.temperature to temperature),
                   unit = U2(distance, mass) to time)

    operator fun div(that: ThermalExpansionCoefficientAmount<K>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
