package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Ampere.AMPERE
import org.pcb.unit.base.Gram.GRAM
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.MagneticFluxDensity
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MagneticFluxDensityAmount<I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<I, T>>)
    : Amount<MagneticFluxDensity>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = MagneticFluxDensity::class,
                                      units = mapOf(unit.major to 1,
                                                    unit.minor.first to -1,
                                                    unit.minor.second to -2)))
{
    val mass = unit.major
    val electricCurrent = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticFluxDensity>) =
            MagneticFluxDensityAmount(amount = amount * unit.scale,
                                      unit = kilo(GRAM) to U2(AMPERE, SECOND))
    }

    // region -> MagneticFluxDensity

    override operator fun <S : Unit<Scalar>> times(that: Number) =
        MagneticFluxDensityAmount(amount = amount * that.toBigDecimal(),
                                  unit = mass to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: Number) =
        MagneticFluxDensityAmount(amount = amount / that.toBigDecimal(),
                                  unit = mass to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = mass to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = mass to U2(electricCurrent, time))

    @JvmName("plusMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: MagneticFluxDensityAmount<I, M, T>) =
        MagneticFluxDensityAmount(amount = amount + that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = mass to U2(electricCurrent, time))

    operator fun plus(that: MagneticFluxDensityAmount<I, M, T>) =
        MagneticFluxDensityAmount(amount = amount + that.amount,
                                  unit = mass to U2(electricCurrent, time))

    @JvmName("minusMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: MagneticFluxDensityAmount<I, M, T>) =
        MagneticFluxDensityAmount(amount = amount - that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = mass to U2(electricCurrent, time))

    operator fun minus(that: MagneticFluxDensityAmount<I, M, T>) =
        MagneticFluxDensityAmount(amount = amount - that.amount,
                                  unit = mass to U2(electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("divMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<I, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MagneticFluxDensityAmount<I, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> ElectricCurrentDensity

    @JvmName("divInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: InductanceAmount<L, I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount
                                              / convert(that.mass to mass)
                                              * convert(that.electricCurrent to electricCurrent,
                                                        that.electricCurrent to electricCurrent,
                                                        that.time to time,
                                                        that.time to time),
                                     unit = electricCurrent to that.distance)

    operator fun <L : Unit<Distance>> div(that: InductanceAmount<L, I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount / that.amount,
                                     unit = electricCurrent to that.distance)

    // endregion

    // region -> ElectricDisplacementField

    @JvmName("timesElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount
                                                 * convert(that.electricCurrent to electricCurrent,
                                                           that.electricCurrent to electricCurrent,
                                                           that.time to time,
                                                           that.time to time,
                                                           that.time to time)
                                                 / convert(that.mass to mass),
                                        unit = U2(electricCurrent, time) to that.distance)

    operator fun <L : Unit<Distance>> times(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount,
                                        unit = U2(electricCurrent, time) to that.distance)

    @JvmName("divElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount
                                                 / convert(that.mass to mass)
                                                 * convert(that.electricCurrent to electricCurrent,
                                                           that.electricCurrent to electricCurrent,
                                                           that.time to time,
                                                           that.time to time,
                                                           that.time to time),
                                        unit = U2(electricCurrent, time) to that.distance)

    operator fun <L : Unit<Distance>> div(that: ElectricResistanceAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount,
                                        unit = U2(electricCurrent, time) to that.distance)

    // endregion

    // region -> ElectricFieldStrength

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.time to time),
                                    unit = U2(that.distance, mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>> div(that: PaceAmount<L, T>) =
        ElectricFieldStrengthAmount(amount = amount / that.amount,
                                    unit = U2(that.distance, mass) to U2(electricCurrent, time))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount
                                             / convert(that.time to time),
                                    unit = U2(that.distance, mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>> times(that: SpeedAmount<L, T>) =
        ElectricFieldStrengthAmount(amount = amount * that.amount,
                                    unit = U2(that.distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> ElectricResistance

    @JvmName("divElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricResistanceAmount(amount = amount / that.amount
                                          / convert(that.electricCurrent to electricCurrent,
                                                    that.time to time),
                                 unit = U2(that.distance, mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        ElectricResistanceAmount(amount = amount / that.amount,
                                 unit = U2(that.distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> Energy

    @JvmName("timesMagneticDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: MagneticDipoleMomentAmount<L, I>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.electricCurrent to electricCurrent),
                     unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: MagneticDipoleMomentAmount<L, I>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, mass) to time)

    // endregion

    // region -> EnergyDensity

    @JvmName("timesMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: MagneticFieldStrengthAmount<L, I>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.electricCurrent to electricCurrent),
                            unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> times(that: MagneticFieldStrengthAmount<L, I>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(that.distance, time))

    // endregion

    // region -> EnergyDensityGradient

    @JvmName("timesElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> times(that: ElectricCurrentDensityAmount<L, I>) =
        EnergyDensityGradientAmount(amount = amount * that.amount
                                             * convert(that.electricCurrent to electricCurrent),
                                    unit = mass to U2(that.distance, time))

    operator fun <L : Unit<Distance>> times(that: ElectricCurrentDensityAmount<L, I>) =
        EnergyDensityGradientAmount(amount = amount * that.amount,
                                    unit = mass to U2(that.distance, time))

    // endregion

    // region -> FuelEfficiency

    @JvmName("divMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<L, I, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time),
                             unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: MagneticFluxAmount<L, I, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> Inductance

    @JvmName("divElectricCurrentDensity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: ElectricCurrentDensityAmount<L, I>) =
        InductanceAmount(amount = amount / that.amount
                                  / convert(that.electricCurrent to electricCurrent),
                         unit = U2(that.distance, mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>> div(that: ElectricCurrentDensityAmount<L, I>) =
        InductanceAmount(amount = amount / that.amount,
                         unit = U2(that.distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("divPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: PermeabilityAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.mass to mass)
                                             * convert(that.electricCurrent to electricCurrent,
                                                       that.electricCurrent to electricCurrent,
                                                       that.time to time,
                                                       that.time to time),
                                    unit = electricCurrent to that.distance)

    operator fun <L : Unit<Distance>> div(that: PermeabilityAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = electricCurrent to that.distance)

    // endregion

    // region -> MagneticFlux

    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.distance, mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(that.distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("timesElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<I, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.electricCurrent to electricCurrent,
                                              that.time to time),
                           unit = mass to time)

    operator fun times(that: ElectricChargeAmount<I, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> Momentum

    @JvmName("timesElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricDipoleMomentAmount<L, I, T>) =
        MomentumAmount(amount = amount * that.amount
                                * convert(that.electricCurrent to electricCurrent,
                                          that.time to time),
                       unit = U2(that.distance, mass) to time)

    operator fun <L : Unit<Distance>> times(that: ElectricDipoleMomentAmount<L, I, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to that.distance)

    operator fun <L : Unit<Distance>> div(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> Permeability

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        PermeabilityAmount(amount = amount / that.amount
                                    / convert(that.electricCurrent to electricCurrent),
                           unit = U2(that.distance, mass) to U2(electricCurrent, time))

    operator fun <L : Unit<Distance>> div(that: MagneticFieldStrengthAmount<L, I>) =
        PermeabilityAmount(amount = amount / that.amount,
                           unit = U2(that.distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> RadiantExposure

    @JvmName("timesElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<I>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.electricCurrent to electricCurrent),
                              unit = mass to time)

    operator fun times(that: ElectricCurrentAmount<I>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    // endregion
}
