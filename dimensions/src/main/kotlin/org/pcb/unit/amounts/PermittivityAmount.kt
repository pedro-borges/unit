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
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.Permittivity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PermittivityAmount<L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<U2<T, I>, U2<M, L>>)
    : Amount<Permittivity>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = Permittivity::class,
                               units = mapOf(unit.major.first to 4,
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
        operator fun invoke(amount: Number, unit: Unit<Permittivity>) =
            PermittivityAmount(amount = amount * unit.scale,
                               unit = U2(SECOND, AMPERE) to U2(kilo(GRAM), METER))
    }

    // region -> Convert

    fun convertTo(unit: Unit<Permittivity>) =
        PermittivityAmount(amount = amount * convert(this.unit to unit),
                           unit = unit)

    // endregion

    // region -> Permittivity

    override operator fun times(that: Number) =
        PermittivityAmount(amount = amount * that.toBigDecimal(),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun div(that: Number) =
        PermittivityAmount(amount = amount / that.toBigDecimal(),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PermittivityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PermittivityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("plusPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> plus(that: PermittivityAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount + that.amount
                                    * convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time)
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance,
                                              that.mass to mass),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun plus(that: PermittivityAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount + that.amount,
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("minusPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> minus(that: PermittivityAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount - that.amount
                                    * convert(that.electricCurrent to electricCurrent,
                                              that.electricCurrent to electricCurrent,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time)
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.distance to distance,
                                              that.mass to mass),
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun minus(that: PermittivityAmount<L, I, M, T>) =
        PermittivityAmount(amount = amount - that.amount,
                           unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> Scalar

    @JvmName("divPermittivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: PermittivityAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount
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
                     unit = NUMERIC)

    operator fun div(that: PermittivityAmount<L, I, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> Capacitance

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        CapacitanceAmount(amount = amount * that.amount
                                   * convert(that.distance to distance),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: DistanceAmount<L>) =
        CapacitanceAmount(amount = amount * that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        CapacitanceAmount(amount = amount / that.amount
                                   * convert(that.distance to distance),
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: SpacialFrequencyAmount<L>) =
        CapacitanceAmount(amount = amount / that.amount,
                          unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> ElectricConductance

    @JvmName("divPace")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: PaceAmount<L, T>) =
        ElectricConductanceAmount(amount = amount / that.amount
                                           / convert(that.time to time)
                                           * convert(that.distance to distance),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: PaceAmount<L, T>) =
        ElectricConductanceAmount(amount = amount / that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("timesSpeed")
    operator fun <L : Unit<Distance>, T : Unit<Time>> times(that: SpeedAmount<L, T>) =
        ElectricConductanceAmount(amount = amount * that.amount
                                           * convert(that.distance to distance)
                                           / convert(that.time to time),
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: SpeedAmount<L, T>) =
        ElectricConductanceAmount(amount = amount * that.amount,
                                  unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> ElectricConductivity

    @JvmName("timesFrequency")
    operator fun <T : Unit<Time>> times(that: FrequencyAmount<T>) =
        ElectricConductivityAmount(amount = amount * that.amount
                                            / convert(that.time to time),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun times(that: FrequencyAmount<T>) =
        ElectricConductivityAmount(amount = amount * that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    @JvmName("divTime")
    operator fun <T : Unit<Time>> div(that: TimeAmount<T>) =
        ElectricConductivityAmount(amount = amount / that.amount
                                            / convert(that.time to time),
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    operator fun div(that: TimeAmount<T>) =
        ElectricConductivityAmount(amount = amount / that.amount,
                                   unit = U2(time, electricCurrent) to U2(mass, distance))

    // endregion

    // region -> ElectricDisplacementField

    @JvmName("timesElectricFieldStrength")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount
                                                 * convert(that.distance to distance,
                                                           that.mass to mass)
                                                 / convert(that.electricCurrent to electricCurrent,
                                                           that.time to time,
                                                           that.time to time,
                                                           that.time to time),
                                        unit = U2(electricCurrent, time) to distance)

    operator fun times(that: ElectricFieldStrengthAmount<L, I, M, T>) =
        ElectricDisplacementFieldAmount(amount = amount * that.amount,
                                        unit = U2(electricCurrent, time) to distance)

    // endregion

    // region -> MolarConductivity

    @JvmName("timesCatalyticEfficiency")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarConductivityAmount(amount = amount * that.amount
                                         * convert(that.distance to distance,
                                                   that.distance to distance,
                                                   that.distance to distance)
                                         / convert(that.time to time),
                                unit = U2(time, electricCurrent) to U2(mass, that.substance))

    operator fun <N : Unit<Substance>> times(that: CatalyticEfficiencyAmount<L, N, T>) =
        MolarConductivityAmount(amount = amount * that.amount,
                                unit = U2(time, electricCurrent) to U2(mass, that.substance))

    // endregion

    // region -> Pace

    @JvmName("divElectricConductance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time)
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass),
                   unit = time to distance)

    operator fun div(that: ElectricConductanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount / that.amount,
                   unit = time to distance)

    @JvmName("timesElectricResistance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> times(that: ElectricResistanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass)
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time),
                   unit = time to distance)

    operator fun times(that: ElectricResistanceAmount<L, I, M, T>) =
        PaceAmount(amount = amount * that.amount,
                   unit = time to distance)

    // endregion

    // region -> SpacialFrequency

    @JvmName("divCapacitance")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: CapacitanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.electricCurrent to electricCurrent,
                                                  that.electricCurrent to electricCurrent,
                                                  that.time to time,
                                                  that.time to time,
                                                  that.time to time,
                                                  that.time to time)
                                        * convert(that.distance to distance,
                                                  that.distance to distance,
                                                  that.mass to mass),
                               unit = NUMERIC to distance)

    operator fun div(that: CapacitanceAmount<L, I, M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Time

    @JvmName("divElectricConductivity")
    operator fun <L : Unit<Distance>, I : Unit<ElectricCurrent>, M : Unit<Mass>, T : Unit<Time>> div(that: ElectricConductivityAmount<L, I, M, T>) =
        TimeAmount(amount = amount / that.amount
                            / convert(that.electricCurrent to electricCurrent,
                                      that.electricCurrent to electricCurrent,
                                      that.time to time,
                                      that.time to time,
                                      that.time to time)
                            * convert(that.distance to distance,
                                      that.distance to distance,
                                      that.distance to distance,
                                      that.mass to mass),
                   unit = time)

    operator fun div(that: ElectricConductivityAmount<L, I, M, T>) =
        TimeAmount(amount = amount / that.amount,
                   unit = time)

    // endregion
}
