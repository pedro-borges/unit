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
import org.pcb.unit.dimensions.ElectricConductance
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricConductanceAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<T, I>, U2<M, L>>)
    : Amount<ElectricConductance>(amount = amount.toBigDecimal(),
                                  unit = CompositeUnit(
                                      domain = ElectricConductance::class,
                                      units = mapOf(unit.major.first to 3,
                                                    unit.major.second to 2,
                                                    unit.minor.first to -1,
                                                    unit.minor.second to -2)))
{
    val time = unit.major.first
    val electricCurrent = unit.major.second
    val mass = unit.minor.first
    val distance = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricConductance>) =
            ElectricConductanceAmount(amount = amount * unit.scale,
                                      unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
    }

    // region -> ElectricConductance

    override operator fun <S : Unit<Scalar>> times(that: Number) =
        ElectricConductanceAmount(amount = amount * that.toBigDecimal(),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> div(that: Number) =
        ElectricConductanceAmount(amount = amount / that.toBigDecimal(),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricConductanceAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricConductanceAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("plusElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount + that.amount
                                           * convert(that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time)
                                           / convert(that.distance to distance,
                                                     that.distance to distance,
                                                     that.mass to mass),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun plus(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount + that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("minusElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount - that.amount
                                           * convert(that.electricCurrent to electricCurrent,
                                                     that.electricCurrent to electricCurrent,
                                                     that.time to time,
                                                     that.time to time,
                                                     that.time to time)
                                           / convert(that.distance to distance,
                                                     that.distance to distance,
                                                     that.mass to mass),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun minus(that: ElectricConductanceAmount<L, I, M, T>) =
        ElectricConductanceAmount(amount = amount - that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Scalar

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: ElectricConductanceAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        ScalarAmount(amount = amount * that.amount
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass)
                              / convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun times(that: ElectricResistanceAmount<L, I, M, T>) =
        ScalarAmount(amount = amount * that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Capacitance

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        CapacitanceAmount(amount = amount / that.amount
                                   * convert(that.time to time),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: FrequencyAmount<T>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        CapacitanceAmount(amount = amount * that.amount
                                   * convert(that.time to time),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: TimeAmount<T>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Distance

    @JvmName("divElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        DistanceAmount(amount = amount / that.amount
                                / convert(that.electricCurrent to electricCurrent,
                                          that.electricCurrent to electricCurrent,
                                          that.time to time,
                                          that.time to time,
                                          that.time to time)
                                * convert(that.distance to distance,
                                          that.distance to distance,
                                          that.distance to distance,
                                          that.mass to mass),
                       unit = distance)

    operator fun div(that: ElectricConductivityAmount<L, I, M, T>) =
        DistanceAmount(amount = amount / that.amount,
                       unit = distance)

    // endregion

    // region -> ElectricCharge

    @JvmName("timesMagneticFlux")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount
                                      * convert(that.distance to distance,
                                                that.distance to distance,
                                                that.mass to mass)
                                      / convert(that.electricCurrent to electricCurrent,
                                                that.time to time,
                                                that.time to time),
                             unit = U2(electricCurrent, time))

    operator fun times(that: MagneticFluxAmount<L, I, M, T>) =
        ElectricChargeAmount(amount = amount * that.amount,
                             unit = U2(electricCurrent, time))

    // endregion

    // region -> ElectricConductivity

    @JvmName("divDistance")
    operator fun <L : Unit<Distance>> div(that: DistanceAmount<L>) =
        ElectricConductivityAmount(amount = amount / that.amount
                                            / convert(that.distance to distance),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: DistanceAmount<L>) =
        ElectricConductivityAmount(amount = amount / that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("timesSpacialFrequency")
    operator fun <L : Unit<Distance>> times(that: SpacialFrequencyAmount<L>) =
        ElectricConductivityAmount(amount = amount * that.amount
                                            / convert(that.distance to distance),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: SpacialFrequencyAmount<L>) =
        ElectricConductivityAmount(amount = amount * that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> ElectricCurrent

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount * that.amount
                                       * convert(that.distance to distance,
                                                 that.distance to distance,
                                                 that.mass to mass)
                                       / convert(that.electricCurrent to electricCurrent,
                                                 that.time to time,
                                                 that.time to time,
                                                 that.time to time),
                              unit = electricCurrent)

    operator fun times(that: ElectricTensionAmount<L, I, M, T>) =
        ElectricCurrentAmount(amount = amount * that.amount,
                              unit = electricCurrent)

    // endregion

    // region -> ElectricDisplacementField

    @JvmName("timesMagneticFluxDensity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount
                                                 * convert(that.mass to mass)
                                                 / convert(that.electricCurrent to electricCurrent,
                                                           that.time to time,
                                                           that.time to time),
                                        unit = U2(electricCurrent, time) to distance)

    operator fun times(that: MagneticFluxDensityAmount<I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount,
                                        unit = U2(electricCurrent, time) to distance)

    // endregion

    // region -> Frequency

    @JvmName("divCapacitance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: CapacitanceAmount<L, I, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.electricCurrent to electricCurrent,
                                           that.electricCurrent to electricCurrent,
                                           that.time to time,
                                           that.time to time,
                                           that.time to time,
                                           that.time to time)
                                 * convert(that.distance to distance,
                                           that.distance to distance,
                                           that.mass to mass),
                        unit = NUMERIC to time)

    operator fun div(that: CapacitanceAmount<L, I, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount
                                             * convert(that.distance to distance,
                                                       that.mass to mass)
                                             / convert(that.electricCurrent to electricCurrent,
                                                       that.time to time,
                                                       that.time to time,
                                                       that.time to time),
                                    unit = electricCurrent to distance)

    operator fun times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = electricCurrent to distance)

    // endregion

    // region -> Pace

    @JvmName("timesPermeability")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: PermeabilityAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.mass to mass)
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun times(that: PermeabilityAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount,
                   unit = time to distance)

    // endregion

    // region -> Permittivity

    @JvmName("timesPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: PaceAmount<L, T>) =
        PermittivityAmount(amount = amount * that.amount
                                    * convert(that.time to time)
                                    / convert(that.distance to distance),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: PaceAmount<L, T>) =
        PermittivityAmount(amount = amount * that.amount,
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("divSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: SpeedAmount<L, T>) =
        PermittivityAmount(amount = amount / that.amount
                                    / convert(that.distance to distance)
                                    * convert(that.time to time),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: SpeedAmount<L, T>) =
        PermittivityAmount(amount = amount / that.amount,
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Speed

    @JvmName("divPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: PermittivityAmount<L, I, M, T>) =
        SpeedAmount(amount = amount / that.amount
                             / convert(that.electricCurrent to electricCurrent,
                                       that.electricCurrent to electricCurrent,
                                       that.time to time,
                                       that.time to time,
                                       that.time to time,
                                       that.time to time)
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance,
                                       that.mass to mass),
                    unit = distance to time)

    operator fun div(that: PermittivityAmount<L, I, M, T>) =
        SpeedAmount(amount = amount / that.amount,
                    unit = distance to time)

    // endregion

    // region -> Time

    @JvmName("timesInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        TimeAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time),
                   unit = time)

    operator fun times(that: InductanceAmount<L, I, M, T>) =
        TimeAmount(amount = amount * that.amount,
                   unit = time)

    // endregion
}
