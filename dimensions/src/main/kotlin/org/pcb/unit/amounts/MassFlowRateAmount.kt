package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.MassFlowRate
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MassFlowRateAmount<M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, T>)
    : Amount<MassFlowRate>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = MassFlowRate::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor to -1)))
{
    val mass = unit.major
    val time = unit.minor

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MassFlowRate>) =
            MassFlowRateAmount(amount = amount * unit.scale,
                               unit = kilo(GRAM) to SECOND)
    }

    // region -> MassFlowRate

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = mass to time)

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MassFlowRateAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = mass to time)

    @JvmName("plusMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> plus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount + that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun plus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount + that.amount,
                           unit = mass to time)

    @JvmName("minusMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> minus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount - that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.time to time),
                           unit = mass to time)

    operator fun minus(that: MassFlowRateAmount<M, T>) =
        MassFlowRateAmount(amount = amount - that.amount,
                           unit = mass to time)

    // endregion

    // region -> Scalar

    @JvmName("divMassFlowRate")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: MassFlowRateAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MassFlowRateAmount<M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> ElectricCharge

    @JvmName("divMagneticFluxDensity")
    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<EC, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time),
                             unit = U2(that.electricCurrent, time))

    operator fun <EC : Unit<ElectricCurrent>> div(that: MagneticFluxDensityAmount<EC, M, T>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(that.electricCurrent, time))

    // endregion

    // region -> Force

    @JvmName("divPace")
    operator fun <D : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<D, T>) =
        ForceAmount(amount = amount / that.amount
                             / convert(that.time to time),
                    unit = U2(that.distance, mass) to time)

    operator fun <D : Unit<Distance>> div(that: PaceAmount<D, T>) =
        ForceAmount(amount = amount / that.amount,
                    unit = U2(that.distance, mass) to time)

    @JvmName("timesSpeed")
    operator fun <D : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<D, T>) =
        ForceAmount(amount = amount * that.amount
                             / convert(that.time to time),
                    unit = U2(that.distance, mass) to time)

    operator fun <D : Unit<Distance>> times(that: SpeedAmount<D, T>) =
        ForceAmount(amount = amount * that.amount,
                    unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Frequency

    @JvmName("divMass")
    operator fun <M : Unit<Mass>> div(that: MassAmount<M>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass),
                        unit = NUMERIC to time)

    operator fun div(that: MassAmount<M>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MagneticFluxDensity

    @JvmName("divElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount
                                           / convert(that.time to time),
                                  unit = mass to U2(that.electricCurrent, time))

    operator fun <EC : Unit<ElectricCurrent>> div(that: ElectricChargeAmount<EC, T>) =
        MagneticFluxDensityAmount(amount = amount / that.amount,
                                  unit = mass to U2(that.electricCurrent, time))

    // endregion

    // region -> Mass

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        MassAmount(amount = amount / that.amount
                            * convert(that.time to time),
                   unit = mass)

    operator fun div(that: FrequencyAmount<T>) =
        MassAmount(amount = amount / that.amount,
                   unit = mass)

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        MassAmount(amount = amount * that.amount
                            * convert(that.time to time),
                   unit = mass)

    operator fun times(that: TimeAmount<T>) =
        MassAmount(amount = amount * that.amount,
                   unit = mass)

    // endregion

    // region -> Momentum

    operator fun <D : Unit<Distance>> times(that: DistanceAmount<D>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(that.distance, mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divForce")
    operator fun <D : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: ForceAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.time to time,
                                      that.time to time),
                   unit = time to that.distance)

    operator fun <D : Unit<Distance>> div(that: ForceAmount<D, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to that.distance)

    // endregion

    // region -> VolumetricFlow

    @JvmName("timesSpecificVolume")
    operator fun <D : Unit<Distance>, M : Unit<Mass>> times(that: SpecificVolumeAmount<D, M>) =
        VolumetricFlowAmount(amount = amount * that.amount
                                      / convert(that.mass to mass),
                             unit = that.distance to time)

    operator fun <D : Unit<Distance>> times(that: SpecificVolumeAmount<D, M>) =
        VolumetricFlowAmount(amount = amount * that.amount,
                             unit = that.distance to time)

    // endregion
}
