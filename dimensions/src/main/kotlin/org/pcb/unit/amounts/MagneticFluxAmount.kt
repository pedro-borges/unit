package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Meter.METER
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.MagneticFlux
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MagneticFluxAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<L, M>, U2<I, T>>)
    : Amount<MagneticFlux>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = MagneticFlux::class,
                               units = mapOf(unit.major.first to 2,
                                             unit.major.second to 1,
                                             unit.minor.first to -1,
                                             unit.minor.second to -2)))
{
    val distance = unit.major.first
    val mass = unit.major.second
    val electricCurrent = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFlux>) =
            MagneticFluxAmount(amount = amount * unit.scale,
                               unit = U2(METER, kilo(GRAM)) to U2(AMPERE, SECOND))
    }

    // region -> Convert

    fun convertTo(unit: Unit<MagneticFlux>) =
        MagneticFluxAmount(amount = amount * convert(this.unit to unit),
                           unit = unit)

    // endregion

    // region -> MagneticFlux

    override operator fun times(that: Number) =
        MagneticFluxAmount(amount = amount * that.toBigDecimal(),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    override operator fun div(that: Number) =
        MagneticFluxAmount(amount = amount / that.toBigDecimal(),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("plusMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: MagneticFluxAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount + that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.mass to mass)
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun plus(that: MagneticFluxAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount + that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("minusMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: MagneticFluxAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount - that.amount
                                    * convert(that.distance to distance,
                                              that.distance to distance,
                                              that.mass to mass)
                                    / convert(that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time),
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun minus(that: MagneticFluxAmount<L, I, M, T>) =
        MagneticFluxAmount(amount = amount - that.amount,
                           unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("divMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              * convert(that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MagneticFluxAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.distance to distance,
                                          that.mass to mass)
                                * convert(that.electricCurrent to electricCurrent,
                                          that.time to time,
                                          that.time to time,
                                          that.time to time),
                       unit = U2(distance, time))

    operator fun div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Action

    @JvmName("timesElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<I, T>) =
        ActionAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent,
                                        that.time to time),
                     unit = U2(distance, mass) to time)

    operator fun times(that: ElectricChargeAmount<I, T>) =
        ActionAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Area

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time),
                   unit = distance)

    operator fun div(that: MagneticFluxDensityAmount<I, M, T>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time)
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass),
                             unit = U2(electricCurrent, time))

    operator fun times(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    @JvmName("divElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = U2(electricCurrent, time))

    operator fun div(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> ElectricCurrent

    @JvmName("divInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: InductanceAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass)
                                       * convert(that.electricCurrent to electricCurrent,
                                                 that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun div(that: InductanceAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.distance to distance,
                                                       that.time to time),
                                    unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: AbsementAmount<L, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          / convert(that.electricCurrent to electricCurrent,
                                                    that.time to time),
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: ElectricChargeAmount<I, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> ElectricTension

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ElectricTensionAmount(amount = amount * that.amount
                                       / convert(that.time to time),
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun times(that: FrequencyAmount<T>) =
        ElectricTensionAmount(amount = amount * that.amount,
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricTensionAmount(amount = amount / that.amount
                                       / convert(that.time to time),
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: TimeAmount<T>) =
        ElectricTensionAmount(amount = amount / that.amount,
                              unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<I>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent),
                     unit = U2(distance, mass) to time)

    operator fun times(that: ElectricCurrentAmount<I>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, mass) to time)

    // endregion

    // region -> Force

    @JvmName("timesMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        ForceAmount(amount = amount * that.amount
                             * convert(that.electricCurrent to electricCurrent)
                             / convert(that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: MagneticFieldStrengthAmount<L, I>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> Inductance

    @JvmName("divElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        InductanceAmount(amount = amount / that.amount
                                  / convert(that.electricCurrent to electricCurrent),
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    operator fun div(that: ElectricCurrentAmount<I>) =
        InductanceAmount(amount = amount / that.amount,
                         unit = U2(distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        MagneticFluxDensityAmount(amount = amount / that.amount
                                           / convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = mass to U2(electricCurrent, time))

    operator fun div(that: AreaAmount<L>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(electricCurrent, time))

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        MagneticFluxDensityAmount(amount = amount * that.amount
                                           / convert(that.distance to distance,
                                                     that.distance to distance),
                                  unit = mass to U2(electricCurrent, time))

    operator fun times(that: FuelEfficiencyAmount<L>) =
        MagneticFluxDensityAmount(amount = amount * that.amount,
                                  unit = mass to U2(electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("timesElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDisplacementFieldAmount<L, I, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.electricCurrent to electricCurrent,
                                              that.time to time)
                                    / convert(that.distance to distance,
                                              that.distance to distance),
                           unit = mass to time)

    operator fun times(that: ElectricDisplacementFieldAmount<L, I, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: ElectricCurrentDensityAmount<L, I>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.electricCurrent to electricCurrent)
                                       / convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = mass to time)

    operator fun times(that: ElectricCurrentDensityAmount<L, I>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    // endregion

    // region -> Time

    @JvmName("divElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricTensionAmount<L, I, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            * convert(that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun div(that: ElectricTensionAmount<L, I, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
