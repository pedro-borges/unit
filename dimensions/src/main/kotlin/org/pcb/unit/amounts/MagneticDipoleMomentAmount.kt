package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.U3
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Ampere.AMPERE
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.MagneticDipoleMoment
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class MagneticDipoleMomentAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>>(
        amount: Number,
        unit: U2<L, I>)
    : Amount<MagneticDipoleMoment>(amount = amount.toBigDecimal(),
                                   unit = CompositeUnit(
                                       domain = MagneticDipoleMoment::class,
                                       units = mapOf(unit.first to 2,
                                                     unit.second to 1)))
{
    val distance = unit.first
    val electricCurrent = unit.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<MagneticDipoleMoment>) =
            MagneticDipoleMomentAmount(amount = amount * unit.scale,
                                       unit = U2(METER, AMPERE))
    }

    // region -> MagneticDipoleMoment

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                   unit = U2(distance, electricCurrent))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        MagneticDipoleMomentAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                   unit = U2(distance, electricCurrent))

    @JvmName("plusMagneticDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> plus(that: MagneticDipoleMomentAmount<L, I>) =
        MagneticDipoleMomentAmount(amount = amount + that.amount
                                            * convert(that.distance to distance,
                                                      that.distance to distance,
                                                      that.electricCurrent to electricCurrent),
                                   unit = U2(distance, electricCurrent))

    operator fun plus(that: MagneticDipoleMomentAmount<L, I>) =
        MagneticDipoleMomentAmount(amount = amount + that.amount,
                                   unit = U2(distance, electricCurrent))

    @JvmName("minusMagneticDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> minus(that: MagneticDipoleMomentAmount<L, I>) =
        MagneticDipoleMomentAmount(amount = amount - that.amount
                                            * convert(that.distance to distance,
                                                      that.distance to distance,
                                                      that.electricCurrent to electricCurrent),
                                   unit = U2(distance, electricCurrent))

    operator fun minus(that: MagneticDipoleMomentAmount<L, I>) =
        MagneticDipoleMomentAmount(amount = amount - that.amount,
                                   unit = U2(distance, electricCurrent))

    // endregion

    // region -> Scalar

    @JvmName("divMagneticDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticDipoleMomentAmount<L, I>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.distance to distance,
                                        that.electricCurrent to electricCurrent),
                     unit = NUMERIC)

    operator fun div(that: MagneticDipoleMomentAmount<L, I>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Area

    @JvmName("divElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        AreaAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent),
                   unit = distance)

    operator fun div(that: ElectricCurrentAmount<I>) =
        AreaAmount(amount = amount / that.amount,
                   unit = distance)

    // endregion

    // region -> ElectricCurrent

    @JvmName("divArea")
    operator fun <L : Unit<Distance>> div(that: AreaAmount<L>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = electricCurrent)

    operator fun div(that: AreaAmount<L>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    @JvmName("timesFuelEfficiency")
    operator fun <L : Unit<Distance>> times(that: FuelEfficiencyAmount<L>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       / convert(that.distance to distance,
                                                 that.distance to distance),
                              unit = electricCurrent)

    operator fun times(that: FuelEfficiencyAmount<L>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricDipoleMoment

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount
                                            / convert(that.distance to distance),
                                   unit = U3(distance, electricCurrent, that.time))

    operator fun <T : Unit<Time>> times(that: PaceAmount<L, T>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U3(distance, electricCurrent, that.time))

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ElectricDipoleMomentAmount(amount = amount / that.amount
                                            / convert(that.distance to distance),
                                   unit = U3(distance, electricCurrent, that.time))

    operator fun <T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        ElectricDipoleMomentAmount(amount = amount / that.amount,
                                   unit = U3(distance, electricCurrent, that.time))

    // endregion

    // region -> Energy

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              / convert(that.electricCurrent to electricCurrent),
                     unit = U2(distance, that.mass) to that.time)

    operator fun <M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to that.time)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount
                                             / convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.distance to distance),
                                    unit = electricCurrent to distance)

    operator fun div(that: VolumeAmount<L>) =
        MagneticFieldStrengthAmount(amount = amount / that.amount,
                                    unit = electricCurrent to distance)

    // endregion

    // region -> Speed

    @JvmName("divElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDipoleMomentAmount<L, I, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.distance to distance,
                                       that.electricCurrent to electricCurrent),
                    unit = distance to that.time)

    operator fun <T : Unit<Time>> div(that: ElectricDipoleMomentAmount<L, I, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to that.time)

    // endregion

    // region -> Volume

    @JvmName("divMagneticFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticFieldStrengthAmount<L, I>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent)
                              * convert(that.distance to distance),
                     unit = distance)

    operator fun div(that: MagneticFieldStrengthAmount<L, I>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion
}
