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
import org.pcb.unit.dimensions.ElectricDipoleMoment
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricDipoleMomentAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>>(
        amount: Number,
        unit: U3<L, I, T>)
    : Amount<ElectricDipoleMoment>(amount = amount.toBigDecimal(),
                                   unit = CompositeUnit(
                                       domain = ElectricDipoleMoment::class,
                                       units = mapOf(unit.first to 1,
                                                     unit.second to 1,
                                                     unit.third to 1)))
{
    val distance = unit.first
    val electricCurrent = unit.second
    val time = unit.third

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricDipoleMoment>) =
            ElectricDipoleMomentAmount(amount = amount * unit.scale,
                                       unit = U3(METER, AMPERE, SECOND))
    }

    // region -> Convert

    fun convertTo(unit: Unit<ElectricDipoleMoment>) =
        ElectricDipoleMomentAmount(amount = amount * convert(this.unit to unit),
                                   unit = unit)

    // endregion

    // region -> ElectricDipoleMoment

    override operator fun times(that: Number) =
        ElectricDipoleMomentAmount(amount = amount * that.toBigDecimal(),
                                   unit = U3(distance, electricCurrent, time))

    override operator fun div(that: Number) =
        ElectricDipoleMomentAmount(amount = amount / that.toBigDecimal(),
                                   unit = U3(distance, electricCurrent, time))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricDipoleMomentAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                   unit = U3(distance, electricCurrent, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricDipoleMomentAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                   unit = U3(distance, electricCurrent, time))

    @JvmName("plusElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> plus(that: ElectricDipoleMomentAmount<L, I, T>) =
        ElectricDipoleMomentAmount(amount = amount + that.amount
                                            * convert(that.distance to distance,
                                                      that.electricCurrent to electricCurrent,
                                                      that.time to time),
                                   unit = U3(distance, electricCurrent, time))

    operator fun plus(that: ElectricDipoleMomentAmount<L, I, T>) =
        ElectricDipoleMomentAmount(amount = amount + that.amount,
                                   unit = U3(distance, electricCurrent, time))

    @JvmName("minusElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> minus(that: ElectricDipoleMomentAmount<L, I, T>) =
        ElectricDipoleMomentAmount(amount = amount - that.amount
                                            * convert(that.distance to distance,
                                                      that.electricCurrent to electricCurrent,
                                                      that.time to time),
                                   unit = U3(distance, electricCurrent, time))

    operator fun minus(that: ElectricDipoleMomentAmount<L, I, T>) =
        ElectricDipoleMomentAmount(amount = amount - that.amount,
                                   unit = U3(distance, electricCurrent, time))

    // endregion

    // region -> Scalar

    @JvmName("divElectricDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDipoleMomentAmount<L, I, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.distance to distance,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: ElectricDipoleMomentAmount<L, I, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Absement

    @JvmName("divElectricCurrent")
    operator fun <I : Unit<ElectricCurrent>> div(that: ElectricCurrentAmount<I>) =
        AbsementAmount(amount = amount / that.amount
                                / convert(that.electricCurrent to electricCurrent),
                       unit = U2(distance, time))

    operator fun div(that: ElectricCurrentAmount<I>) =
        AbsementAmount(amount = amount / that.amount,
                       unit = U2(distance, time))

    // endregion

    // region -> Distance

    @JvmName("divElectricCharge")
    operator fun <I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricChargeAmount<I, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.electricCurrent to electricCurrent,
                                          that.time to time),
                       unit = distance)

    operator fun div(that: ElectricChargeAmount<I, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        ElectricChargeAmount(amount = amount / that.amount
                                      / convert(that.distance to distance),
                             unit = U2(electricCurrent, time))

    operator fun div(that: DistanceAmount<L>) =
        ElectricChargeAmount(amount = amount / that.amount,
                             unit = U2(electricCurrent, time))

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      / convert(that.distance to distance),
                             unit = U2(electricCurrent, time))

    operator fun times(that: SpacialFrequencyAmount<L>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> ElectricCurrent

    @JvmName("divAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: AbsementAmount<L, T>) =
        ElectricCurrentAmount(amount = amount / that.amount
                                       / convert(that.distance to distance,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun div(that: AbsementAmount<L, T>) =
        ElectricCurrentAmount(amount = amount / that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricDisplacementField

    @JvmName("divVolume")
    operator fun <L : Unit<Distance>> div(that: VolumeAmount<L>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount
                                                 / convert(that.distance to distance,
                                                           that.distance to distance,
                                                           that.distance to distance),
                                        unit = U2(electricCurrent, time) to distance)

    operator fun div(that: VolumeAmount<L>) =
        ElectricDisplacementFieldAmount(amount = amount / that.amount,
                                        unit = U2(electricCurrent, time) to distance)

    // endregion

    // region -> Energy

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        EnergyAmount(amount = amount * that.amount
                              * convert(that.distance to distance)
                              / convert(that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        EnergyAmount(amount = amount * that.amount,
                     unit = U2(distance, that.mass) to time)

    // endregion

    // region -> MagneticDipoleMoment

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        MagneticDipoleMomentAmount(amount = amount / that.amount
                                            / convert(that.time to time)
                                            * convert(that.distance to distance),
                                   unit = U2(distance, electricCurrent))

    operator fun div(that: PaceAmount<L, T>) =
        MagneticDipoleMomentAmount(amount = amount / that.amount,
                                   unit = U2(distance, electricCurrent))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount
                                            * convert(that.distance to distance)
                                            / convert(that.time to time),
                                   unit = U2(distance, electricCurrent))

    operator fun times(that: SpeedAmount<L, T>) =
        MagneticDipoleMomentAmount(amount = amount * that.amount,
                                   unit = U2(distance, electricCurrent))

    // endregion

    // region -> Momentum

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        MomentumAmount(amount = amount * that.amount
                                / convert(that.electricCurrent to electricCurrent,
                                          that.time to time,
                                          that.time to time),
                       unit = U2(distance, that.mass) to time)

    operator fun <M : Unit<Mass>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        MomentumAmount(amount = amount * that.amount,
                       unit = U2(distance, that.mass) to time)

    // endregion

    // region -> Pace

    @JvmName("divMagneticDipoleMoment")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>> div(that: MagneticDipoleMomentAmount<L, I>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.distance to distance,
                                      that.distance to distance,
                                      that.electricCurrent to electricCurrent),
                   unit = time to distance)

    operator fun div(that: MagneticDipoleMomentAmount<L, I>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    // endregion

    // region -> Volume

    @JvmName("divElectricDisplacementField")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, T : Unit<Time>> div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        VolumeAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.time to time)
                              * convert(that.distance to distance,
                                        that.distance to distance),
                     unit = distance)

    operator fun div(that: ElectricDisplacementFieldAmount<L, I, T>) =
        VolumeAmount(amount = amount / that.amount,
                     unit = distance)

    // endregion
}
