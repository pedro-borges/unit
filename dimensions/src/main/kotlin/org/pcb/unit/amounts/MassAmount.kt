package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Temperature
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MassAmount<M : Unit<Mass>>(
        amount: Number,
        unit: M)
    : Amount<Mass>(amount = amount.toBigDecimal(),
                   unit = CompositeUnit(
                       domain = Mass::class,
                       units = mapOf(unit to 1)))
{
    val mass = unit

    // region -> Mass

    override operator fun <S : Unit<Scalar>> times(that: Number) =
        MassAmount(amount = amount * that.toBigDecimal(),
                   unit = mass)

    override operator fun <S : Unit<Scalar>> div(that: Number) =
        MassAmount(amount = amount / that.toBigDecimal(),
                   unit = mass)

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                   unit = mass)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                   unit = mass)

    @JvmName("plusMass")
    operator fun <M : Unit<Mass>> plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount
                            * convert(that.mass to mass),
                   unit = mass)

    operator fun plus(that: MassAmount<M>) =
        MassAmount(amount = amount + that.amount,
                   unit = mass)

    @JvmName("minusMass")
    operator fun <M : Unit<Mass>> minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount
                            * convert(that.mass to mass),
                   unit = mass)

    operator fun minus(that: MassAmount<M>) =
        MassAmount(amount = amount - that.amount,
                   unit = mass)

    // endregion

    // region -> Scalar

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: MassAmount<M>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divViscosity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.mass to mass),
                       unit = U2(that.distance, that.time))

    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: ViscosityAmount<L, M, T>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(that.distance, that.time))

    // endregion

    // region -> AngularMomentum

    @JvmName("timesMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount
                                       * convert(that.mass to mass),
                              unit = U2(mass, that.distance) to that.time)

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: MomentumAmount<L, M, T>) =
        AngularMomentumAmount(amount = amount * that.amount,
                              unit = U2(mass, that.distance) to that.time)

    // endregion

    // region -> Area

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: AreaDensityAmount<L, M>) =
        AreaAmount(amount = amount / that.amount,
                   unit = that.distance)

    // endregion

    // region -> AreaDensity

    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to that.distance)

    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        AreaDensityAmount(amount = amount * that.amount,
                          unit = mass to that.distance)

    // endregion

    // region -> Distance

    @JvmName("divLinearDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: LinearDensityAmount<L, M>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.mass to mass),
                       unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: LinearDensityAmount<L, M>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = that.distance)

    // endregion

    // region -> Energy

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpecificEnergyAmount<L, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(that.distance, mass) to that.time)

    // endregion

    // region -> Force

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AccelerationAmount<L, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to that.time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divMomentOfInertia")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MomentOfInertiaAmount<L, M>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass),
                             unit = NUMERIC to that.distance)

    operator fun <L : Unit<Distance>> div(that: MomentOfInertiaAmount<L, M>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> HeatCapacity

    operator fun <L : Unit<Distance>, K : Unit<Temperature>, T : Unit<Time>> times(that: SpecificHeatCapacityAmount<L, K, T>) =
        HeatCapacityAmount(amount = amount * that.amount,
                           unit = U2(that.distance, mass) to U2(that.temperature, that.time))

    // endregion

    // region -> LinearDensity

    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        LinearDensityAmount(amount = amount / that.amount,
                            unit = mass to that.distance)

    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        LinearDensityAmount(amount = amount * that.amount,
                            unit = mass to that.distance)

    // endregion

    // region -> MassDensity

    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        MassDensityAmount(amount = amount / that.amount,
                          unit = mass to that.distance)

    // endregion

    // region -> MassFlowRate

    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to that.time)

    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        MassFlowRateAmount(amount = amount / that.amount,
                           unit = mass to that.time)

    // endregion

    // region -> MolarMass

    operator fun <N : Unit<Substance>> times(that: ReciprocalSubstanceAmount<N>) =
        MolarMassAmount(amount = amount * that.amount,
                        unit = mass to that.substance)

    operator fun <N : Unit<Substance>> div(that: SubstanceAmount<N>) =
        MolarMassAmount(amount = amount / that.amount,
                        unit = mass to that.substance)

    // endregion

    // region -> MomentOfInertia

    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        MomentOfInertiaAmount(amount = amount * that.amount,
                              unit = U2(that.distance, mass))

    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        MomentOfInertiaAmount(amount = amount / that.amount,
                              unit = U2(that.distance, mass))

    // endregion

    // region -> Momentum

    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        MomentumAmount(amount = amount / that.amount,
                       unit = U2(that.distance, mass) to that.time)

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, mass) to that.time)

    // endregion

    // region -> Pace

    @JvmName("divMomentum")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = that.time to that.distance)

    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: MomentumAmount<L, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = that.time to that.distance)

    // endregion

    // region -> Substance

    @JvmName("timesMolality")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> times(that: MolalityAmount<M, N>) =
        SubstanceAmount(amount = amount * that.amount
                                 / convert(that.mass to mass),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> times(that: MolalityAmount<M, N>) =
        SubstanceAmount(amount = amount * that.amount,
                        unit = that.substance)

    @JvmName("divMolarMass")
    operator fun <M : Unit<Mass>, N : Unit<Substance>> div(that: MolarMassAmount<M, N>) =
        SubstanceAmount(amount = amount / that.amount
                                 / convert(that.mass to mass),
                        unit = that.substance)

    operator fun <N : Unit<Substance>> div(that: MolarMassAmount<M, N>) =
        SubstanceAmount(amount = amount / that.amount,
                        unit = that.substance)

    // endregion

    // region -> Time

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.mass to mass),
                   unit = that.time)

    operator fun <T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = that.time)

    // endregion

    // region -> Viscosity

    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        ViscosityAmount(amount = amount / that.amount,
                        unit = mass to U2(that.distance, that.time))

    // endregion

    // region -> Volume

    @JvmName("divMassDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: MassDensityAmount<L, M>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.mass to mass),
                     unit = that.distance)

    operator fun <L : Unit<Distance>> div(that: MassDensityAmount<L, M>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = that.distance)

    @JvmName("timesSpecificVolume")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<L, M>) =
        VolumeAmount(amount = amount * that.amount
                              / convert(that.mass to mass),
                     unit = that.distance)

    operator fun <L : Unit<Distance>> times(that: SpecificVolumeAmount<L, M>) =
        VolumeAmount(amount = amount * that.amount,
                     unit = that.distance)

    // endregion

    // region -> Yank

    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: JerkAmount<L, T>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(that.distance, mass) to that.time)

    // endregion
}
