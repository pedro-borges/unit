package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
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

open class MagneticFluxDensityAmount<EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<EC, T>>)
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

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = mass to U2(electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticFluxDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = mass to U2(electricCurrent, time))

    @JvmName("plusMagneticFluxDensity")
    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: MagneticFluxDensityAmount<EC, M, T>) =
        MagneticFluxDensityAmount(amount = amount + that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = mass to U2(electricCurrent, time))

    operator fun plus(that: MagneticFluxDensityAmount<EC, M, T>) =
        MagneticFluxDensityAmount(amount = amount + that.amount,
                                  unit = mass to U2(electricCurrent, time))

    @JvmName("minusMagneticFluxDensity")
    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: MagneticFluxDensityAmount<EC, M, T>) =
        MagneticFluxDensityAmount(amount = amount - that.amount
                                           * convert(that.mass to mass)
                                           / convert(that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time),
                                  unit = mass to U2(electricCurrent, time))

    operator fun minus(that: MagneticFluxDensityAmount<EC, M, T>) =
        MagneticFluxDensityAmount(amount = amount - that.amount,
                                  unit = mass to U2(electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("divMagneticFluxDensity")
    operator fun <EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxDensityAmount<EC, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: MagneticFluxDensityAmount<EC, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divMagneticFlux")
    operator fun <D : Unit<Distance>, EC : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: MagneticFluxAmount<D, EC, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.mass to mass)
                                      * convert(that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time),
                             unit = NUMERIC to that.distance)

    operator fun <D : Unit<Distance>> div(that: MagneticFluxAmount<D, EC, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to that.distance)

    // endregion

    // region -> MagneticFlux

    operator fun <D : Unit<Distance>> times(that: AreaAmount<D>) =
        MagneticFluxAmount(amount = amount * that.amount,
                           unit = U2(that.distance, mass) to U2(electricCurrent, time))

    operator fun <D : Unit<Distance>> div(that: FuelEfficiencyAmount<D>) =
        MagneticFluxAmount(amount = amount / that.amount,
                           unit = U2(that.distance, mass) to U2(electricCurrent, time))

    // endregion

    // region -> MassFlowRate

    @JvmName("timesElectricCharge")
    operator fun <EC : Unit<ElectricCurrent>, T : Unit<Time>> times(that: ElectricChargeAmount<EC, T>) =
        MassFlowRateAmount(amount = amount * that.amount
                                    * convert(that.electricCurrent to electricCurrent,
                                              that.time to time),
                           unit = mass to time)

    operator fun times(that: ElectricChargeAmount<EC, T>) =
        MassFlowRateAmount(amount = amount * that.amount,
                           unit = mass to time)

    // endregion

    // region -> RadiantExposure

    @JvmName("timesElectricCurrent")
    operator fun <EC : Unit<ElectricCurrent>> times(that: ElectricCurrentAmount<EC>) =
        RadiantExposureAmount(amount = amount * that.amount
                                       * convert(that.electricCurrent to electricCurrent),
                              unit = mass to time)

    operator fun times(that: ElectricCurrentAmount<EC>) =
        RadiantExposureAmount(amount = amount * that.amount,
                              unit = mass to time)

    // endregion
}
