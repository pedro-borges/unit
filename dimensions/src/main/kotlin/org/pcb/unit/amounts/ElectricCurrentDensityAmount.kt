package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.ElectricCurrentDensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricCurrentDensityAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>>(
        amount: Number,
        unit: Pair<I, L>)
    : Amount<ElectricCurrentDensity>(amount = amount.toBigDecimal(),
                                     unit = CompositeUnit(
                                         domain = ElectricCurrentDensity::class,
                                         units = mapOf(unit.major to 1,
                                                       unit.minor to -2)))
{
    val electricCurrent = unit.major
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricCurrentDensity>) =
            ElectricCurrentDensityAmount(amount = amount * unit.scale,
                                         unit = AMPERE to METER)
    }

    // region -> Convert

    fun convertTo(unit: Unit<ElectricCurrentDensity>) =
        ElectricCurrentDensityAmount(amount = amount * convert(this.unit to unit),
                                     unit = unit)

    // endregion

    // region -> ElectricCurrentDensity

    override operator fun times(that: Number) =
        ElectricCurrentDensityAmount(amount = amount * that.toBigDecimal(),
                                     unit = electricCurrent to distance)

    override operator fun div(that: Number) =
        ElectricCurrentDensityAmount(amount = amount / that.toBigDecimal(),
                                     unit = electricCurrent to distance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                     unit = electricCurrent to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                     unit = electricCurrent to distance)

    @JvmName("plusElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> plus(that: ElectricCurrentDensityAmount<L, I>) =
        ElectricCurrentDensityAmount(amount = amount + that.amount
                                              * convert(that.electricCurrent to electricCurrent)
                                              / convert(that.distance to distance,
                                                        that.distance to distance),
                                     unit = electricCurrent to distance)

    operator fun plus(that: ElectricCurrentDensityAmount<L, I>) =
        ElectricCurrentDensityAmount(amount = amount + that.amount,
                                     unit = electricCurrent to distance)

    @JvmName("minusElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> minus(that: ElectricCurrentDensityAmount<L, I>) =
        ElectricCurrentDensityAmount(amount = amount - that.amount
                                              * convert(that.electricCurrent to electricCurrent)
                                              / convert(that.distance to distance,
                                                        that.distance to distance),
                                     unit = electricCurrent to distance)

    operator fun minus(that: ElectricCurrentDensityAmount<L, I>) =
        ElectricCurrentDensityAmount(amount = amount - that.amount,
                                     unit = electricCurrent to distance)

    // endregion

    // region -> Scalar

    @JvmName("divElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent)
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: ElectricCurrentDensityAmount<L, I>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> ElectricConductivity

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount / that.amount
                                            / convert(that.distance to distance)
                                            * convert(that.electricCurrent to electricCurrent),
                                   unit = U2(that.time, electricCurrent) to U2(that.mass, distance))

    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount / that.amount,
                                   unit = U2(that.time, electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = electricCurrent)

    operator fun times(that: AreaAmount<L>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = electricCurrent)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricDisplacementField

    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount,
                                        unit = U2(electricCurrent, that.time) to distance)

    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount,
                                        unit = U2(electricCurrent, that.time) to distance)

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("divElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.electricCurrent to electricCurrent,
                                                       that.electricCurrent to electricCurrent)
                                             * convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.distance to distance),
                                    unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(distance, that.mass) to U2(electricCurrent, that.time))

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        EnergyDensityGradientAmount(amount = amount * that.amount
                                             / convert(that.electricCurrent to electricCurrent),
                                    unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        EnergyDensityGradientAmount(amount = amount * that.amount,
                                    unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> Frequency

    @JvmName("divElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.electricCurrent to electricCurrent)
                                 * convert(that.distance to distance,
                                           that.distance to distance),
                        unit = NUMERIC to that.time)

    operator fun <T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to that.time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.electricCurrent to electricCurrent),
                             unit = NUMERIC to distance)

    operator fun div(that: ElectricCurrentAmount<I>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Intensity

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance,
                                           that.distance to distance)
                                 / convert(that.electricCurrent to electricCurrent),
                        unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = that.mass to that.time)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount
                                             * convert(that.distance to distance),
                                    unit = electricCurrent to distance)

    operator fun times(that: DistanceAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = electricCurrent to distance)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             * convert(that.distance to distance),
                                    unit = electricCurrent to distance)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = electricCurrent to distance)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("timesInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance)
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent),
                                  unit = that.mass to U2(electricCurrent, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = that.mass to U2(electricCurrent, that.time))

    // endregion

    // region -> PowerDensity

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        PowerDensityAmount(amount = amount * that.amount
                                    * convert(that.distance to distance)
                                    / convert(that.electricCurrent to electricCurrent),
                           unit = that.mass to U2(distance, that.time))

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        PowerDensityAmount(amount = amount * that.amount,
                           unit = that.mass to U2(distance, that.time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.electricCurrent to electricCurrent),
                              unit = that.mass to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to that.time)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.electricCurrent to electricCurrent)
                                        * convert(that.distance to distance),
                               unit = NUMERIC to distance)

    operator fun div(that: MagneticFieldStrengthAmount<L, I>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion
}
