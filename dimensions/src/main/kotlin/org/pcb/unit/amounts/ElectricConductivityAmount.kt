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
import org.pcb.unit.dimensions.ElectricConductivity
import org.pcb.unit.dimensions.ElectricCurrent
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class ElectricConductivityAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<T, I>, U2<M, L>>)
    : Amount<ElectricConductivity>(amount = amount.toBigDecimal(),
                                   unit = CompositeUnit(
                                       domain = ElectricConductivity::class,
                                       units = mapOf(unit.major.first to 3,
                                                     unit.major.second to 2,
                                                     unit.minor.first to -1,
                                                     unit.minor.second to -3)))
{
    val time = unit.major.first
    val electricCurrent = unit.major.second
    val mass = unit.minor.first
    val distance = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<ElectricConductivity>) =
            ElectricConductivityAmount(amount = amount * unit.scale,
                                       unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
    }

    // region -> Convert

    fun convertTo(unit: Unit<ElectricConductivity>) =
        ElectricConductivityAmount(amount = amount * convert(this.unit to unit),
                                   unit = unit)

    // endregion

    // region -> ElectricConductivity

    override operator fun times(that: Number) =
        ElectricConductivityAmount(amount = amount * that.toBigDecimal(),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun div(that: Number) =
        ElectricConductivityAmount(amount = amount / that.toBigDecimal(),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        ElectricConductivityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        ElectricConductivityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("plusElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount + that.amount
                                            * convert(that.electricCurrent to electricCurrent,
                                                      that.electricCurrent to electricCurrent,
                                                      that.time to time,
                                                      that.time to time,
                                                      that.time to time)
                                            / convert(that.distance to distance,
                                                      that.distance to distance,
                                                      that.distance to distance,
                                                      that.mass to mass),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun plus(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount + that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("minusElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount - that.amount
                                            * convert(that.electricCurrent to electricCurrent,
                                                      that.electricCurrent to electricCurrent,
                                                      that.time to time,
                                                      that.time to time,
                                                      that.time to time)
                                            / convert(that.distance to distance,
                                                      that.distance to distance,
                                                      that.distance to distance,
                                                      that.mass to mass),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun minus(that: ElectricConductivityAmount<L, I, M, T>) =
        ElectricConductivityAmount(amount = amount - that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Scalar

    @JvmName("divElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.electricCurrent to electricCurrent,
                                        that.electricCurrent to electricCurrent,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time)
                              * convert(that.distance to distance,
                                        that.distance to distance,
                                        that.distance to distance,
                                        that.mass to mass),
                     unit = NUMERIC)

    operator fun div(that: ElectricConductivityAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Capacitance

    @JvmName("timesAbsement")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: AbsementAmount<L, T>) =
        CapacitanceAmount(amount = amount * that.amount
                                   * convert(that.distance to distance,
                                             that.time to time),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: AbsementAmount<L, T>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> ElectricConductance

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        ElectricConductanceAmount(amount = amount * that.amount
                                           * convert(that.distance to distance),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: DistanceAmount<L>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           * convert(that.distance to distance),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: SpacialFrequencyAmount<L>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> ElectricCurrentDensity

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount
                                              * convert(that.distance to distance,
                                                        that.mass to mass)
                                              / convert(that.electricCurrent to electricCurrent,
                                                        that.time to time,
                                                        that.time to time,
                                                        that.time to time),
                                     unit = electricCurrent to distance)

    operator fun times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricCurrentDensityAmount(amount = amount * that.amount,
                                     unit = electricCurrent to distance)

    // endregion

    // region -> Frequency

    @JvmName("divPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: PermittivityAmount<L, I, M, T>) =
        FrequencyAmount(amount = amount / that.amount
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
                        unit = NUMERIC to time)

    operator fun div(that: PermittivityAmount<L, I, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> MagneticFieldStrength

    @JvmName("timesElectricTension")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricTensionAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount
                                             * convert(that.distance to distance,
                                                       that.distance to distance,
                                                       that.mass to mass)
                                             / convert(that.electricCurrent to electricCurrent,
                                                       that.time to time,
                                                       that.time to time,
                                                       that.time to time),
                                    unit = electricCurrent to distance)

    operator fun times(that: ElectricTensionAmount<L, I, M, T>) =
        MagneticFieldStrengthAmount(amount = amount * that.amount,
                                    unit = electricCurrent to distance)

    // endregion

    // region -> MolarConductivity

    @JvmName("timesMolarVolume")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        MolarConductivityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.distance to distance),
                                unit = U2(time, electricCurrent) to U2(mass, that.substance))

    operator fun <N : Unit<Substance>> times(that: MolarVolumeAmount<L, N>) =
        MolarConductivityAmount(amount = amount * that.amount,
                                unit = U2(time, electricCurrent) to U2(mass, that.substance))

    @JvmName("divMolarity")
    operator fun <L : Unit<Distance>, N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        MolarConductivityAmount(amount = amount / that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.distance to distance),
                                unit = U2(time, electricCurrent) to U2(mass, that.substance))

    operator fun <N : Unit<Substance>> div(that: MolarityAmount<L, N>) =
        MolarConductivityAmount(amount = amount / that.amount,
                                unit = U2(time, electricCurrent) to U2(mass, that.substance))

    // endregion

    // region -> Molarity

    @JvmName("divMolarConductivity")
    operator fun <I : Unit<ElectricCurrent>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> div(that: MolarConductivityAmount<I, M, N, T>) =
        MolarityAmount(amount = amount / that.amount
                                / convert(that.electricCurrent to electricCurrent,
                                          that.electricCurrent to electricCurrent,
                                          that.time to time,
                                          that.time to time,
                                          that.time to time)
                                * convert(that.mass to mass),
                       unit = that.substance to distance)

    operator fun <N : Unit<Substance>> div(that: MolarConductivityAmount<I, M, N, T>) =
        MolarityAmount(amount = amount / that.amount,
                       unit = that.substance to distance)

    // endregion

    // region -> Pace

    @JvmName("timesInductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: InductanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun times(that: InductanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount,
                   unit = time to distance)

    // endregion

    // region -> Permittivity

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        PermittivityAmount(amount = amount / that.amount
                                    * convert(that.time to time),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: FrequencyAmount<T>) =
        PermittivityAmount(amount = amount / that.amount,
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        PermittivityAmount(amount = amount * that.amount
                                    * convert(that.time to time),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: TimeAmount<T>) =
        PermittivityAmount(amount = amount * that.amount,
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> SpacialFrequency

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.electricCurrent to electricCurrent,
                                                  that.electricCurrent to electricCurrent,
                                                  that.time to time,
                                                  that.time to time,
                                                  that.time to time)
                                        * convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass),
                               unit = NUMERIC to distance)

    operator fun div(that: ElectricConductanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount * that.amount
                                        * convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass)
                                        / convert(that.electricCurrent to electricCurrent,
                                                  that.electricCurrent to electricCurrent,
                                                  that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun times(that: ElectricResistanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount * that.amount,
                               unit = NUMERIC to distance)

    // endregion
}
