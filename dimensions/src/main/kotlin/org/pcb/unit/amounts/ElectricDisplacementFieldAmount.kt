package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.ElectricDisplacementField
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricDisplacementFieldAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<I, T>, L>)
    : Amount<ElectricDisplacementField>(amount = amount.toBigDecimal(),
                                        unit = CompositeUnit(
                                            domain = ElectricDisplacementField::class,
                                            units = mapOf(unit.major.first to 1,
                                                          unit.major.second to 1,
                                                          unit.minor to -2)))
{
    val electricCurrent = unit.major.first
    val time = unit.major.second
    val distance = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricDisplacementField>) =
            ElectricDisplacementFieldAmount(amount = amount * unit.scale,
                                            unit = U2(AMPERE, SECOND) to METER)
    }

    // region -> Convert

    fun convertTo(unit: Unit<ElectricDisplacementField>) =
        ElectricDisplacementFieldAmount(amount = amount * convert(this.unit to unit),
                                        unit = unit)

    // endregion

    // region -> ElectricDisplacementField

    override operator fun times(that: Number) =
        ElectricDisplacementFieldAmount(amount = amount * that.toBigDecimal(),
                                        unit = U2(electricCurrent, time) to distance)

    override operator fun div(that: Number) =
        ElectricDisplacementFieldAmount(amount = amount / that.toBigDecimal(),
                                        unit = U2(electricCurrent, time) to distance)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                        unit = U2(electricCurrent, time) to distance)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                        unit = U2(electricCurrent, time) to distance)

    @JvmName("plusElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> plus(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricDisplacementFieldAmount(amount = amount + that.amount
                                                 * convert(that.electricCurrent to electricCurrent,
                                                           that.time to time)
                                                 / convert(that.distance to distance,
                                                           that.distance to distance),
                                        unit = U2(electricCurrent, time) to distance)

    operator fun plus(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricDisplacementFieldAmount(amount = amount + that.amount,
                                        unit = U2(electricCurrent, time) to distance)

    @JvmName("minusElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> minus(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricDisplacementFieldAmount(amount = amount - that.amount
                                                 * convert(that.electricCurrent to electricCurrent,
                                                           that.time to time)
                                                 / convert(that.distance to distance,
                                                           that.distance to distance),
                                        unit = U2(electricCurrent, time) to distance)

    operator fun minus(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricDisplacementFieldAmount(amount = amount - that.amount,
                                        unit = U2(electricCurrent, time) to distance)

    // endregion

    // region -> Scalar

    @JvmName("divElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.time to time)
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = NUMERIC)

    operator fun div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = U2(electricCurrent, time))

    operator fun times(that: AreaAmount<L>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance),
                             unit = U2(electricCurrent, time))

    operator fun div(that: FuelEfficiencyAmount<L>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> ElectricConductance

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           * convert(that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = U2(time, electricCurrent) to U2(that.mass, distance))

    operator fun <M : Unit<Mass>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(time, electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> ElectricCurrentDensity

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount
                                              / convert(that.time to time),
                                     unit = electricCurrent to distance)

    operator fun times(that: FrequencyAmount<T>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount,
                                     unit = electricCurrent to distance)

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount
                                              / convert(that.time to time),
                                     unit = electricCurrent to distance)

    operator fun div(that: TimeAmount<T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount,
                                     unit = electricCurrent to distance)

    // endregion

    // region -> ElectricDipoleMoment

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount
                                            * convert(that.distance to distance,
                                                      that.distance to distance,
                                                      that.distance to distance),
                                   unit = U3(distance, electricCurrent, time))

    operator fun times(that: VolumeAmount<L>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U3(distance, electricCurrent, time))

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("divPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: PermittivityAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.electricCurrent to electricCurrent,
                                                       that.electricCurrent to electricCurrent,
                                                       that.time to time,
                                                       that.time to time,
                                                       that.time to time,
                                                       that.time to time)
                                             * convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.distance to distance),
                                    unit = U2(distance, that.mass) to U2(electricCurrent, time))

    operator fun <M : Unit<Mass>> div(that: PermittivityAmount<L, I, M, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(distance, that.mass) to U2(electricCurrent, time))

    // endregion

    // region -> EnergyDensity

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.distance to distance)
                                     / convert(that.electricCurrent to electricCurrent,
                                               that.time to time,
                                               that.time to time,
                                               that.time to time),
                            unit = that.mass to U2(distance, time))

    operator fun <M : Unit<Mass>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = that.mass to U2(distance, time))

    // endregion

    // region -> FuelEfficiency

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.electricCurrent to electricCurrent,
                                                that.time to time),
                             unit = NUMERIC to distance)

    operator fun div(that: ElectricChargeAmount<I, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.time to time)
                                             * convert(that.distance to distance),
                                    unit = electricCurrent to distance)

    operator fun div(that: PaceAmount<L, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = electricCurrent to distance)

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount
                                             * convert(that.distance to distance)
                                             / convert(that.time to time),
                                    unit = electricCurrent to distance)

    operator fun times(that: SpeedAmount<L, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = electricCurrent to distance)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time)
                                           * convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = that.mass to U2(electricCurrent, time))

    operator fun <M : Unit<Mass>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = that.mass to U2(electricCurrent, time))

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount
                                           * convert(that.distance to distance,
                                                     that.distance to distance)
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = that.mass to U2(electricCurrent, time))

    operator fun <M : Unit<Mass>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = that.mass to U2(electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance)
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time),
                           unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: MagneticFluxAmount<L, I, M, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = that.mass to time)

    // endregion

    // region -> Pace

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent)
                            * convert(that.distance to distance),
                   unit = time to distance)

    operator fun div(that: MagneticFieldStrengthAmount<L, I>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Permittivity

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount / that.amount
                                    / convert(that.distance to distance)
                                    * convert(that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(time, electricCurrent) to U2(that.mass, distance))

    operator fun <M : Unit<Mass>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount / that.amount,
                           unit = U2(time, electricCurrent) to U2(that.mass, distance))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance)
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = that.mass to time)

    operator fun <M : Unit<Mass>> times(that: ElectricTensionAmount<L, I, M, T>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = that.mass to time)

    // endregion

    // region -> Time

    @JvmName("divElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent)
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = time)

    operator fun div(that: ElectricCurrentDensityAmount<L, I>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
