package org.pcb.unit.amounts

import org.pcb.unit.Amount
import org.pcb.unit.CompositeUnit
import org.pcb.unit.U2
import org.pcb.unit.Unit
import org.pcb.unit.amounts.base.Gram.GRAM
import org.pcb.unit.amounts.base.Meter.METER
import org.pcb.unit.amounts.base.Numeric.NUMERIC
import org.pcb.unit.amounts.base.Second.SECOND
import org.pcb.unit.convert
import org.pcb.unit.dimensions.Distance
import org.pcb.unit.dimensions.Mass
import org.pcb.unit.dimensions.PowerDensity
import org.pcb.unit.dimensions.Scalar
import org.pcb.unit.dimensions.Substance
import org.pcb.unit.dimensions.Time
import org.pcb.unit.div
import org.pcb.unit.kilo
import org.pcb.unit.major
import org.pcb.unit.minor
import org.pcb.unit.times
import org.pcb.unit.toBigDecimal

open class PowerDensityAmount<L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>>(
        amount: Number,
        unit: Pair<M, U2<L, T>>)
    : Amount<PowerDensity>(amount = amount.toBigDecimal(),
                           unit = CompositeUnit(
                               domain = PowerDensity::class,
                               units = mapOf(unit.major to 1,
                                             unit.minor.first to -1,
                                             unit.minor.second to -3)))
{
    val mass = unit.major
    val distance = unit.minor.first
    val time = unit.minor.second

    companion object
    {
        operator fun invoke(amount: Number, unit: Unit<PowerDensity>) =
            PowerDensityAmount(amount = amount * unit.scale,
                               unit = kilo(GRAM) to U2(METER, SECOND))
    }

    // region -> PowerDensity

    override operator fun <S : Unit<Scalar>> times(that: ScalarAmount<S>) =
        PowerDensityAmount(amount = amount * that.amount * convert(that.scalar to NUMERIC),
                           unit = mass to U2(distance, time))

    override operator fun <S : Unit<Scalar>> div(that: ScalarAmount<S>) =
        PowerDensityAmount(amount = amount / that.amount / convert(that.scalar to NUMERIC),
                           unit = mass to U2(distance, time))

    @JvmName("plusPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> plus(that: PowerDensityAmount<L, M, T>) =
        PowerDensityAmount(amount = amount + that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.distance to distance,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = mass to U2(distance, time))

    operator fun plus(that: PowerDensityAmount<L, M, T>) =
        PowerDensityAmount(amount = amount + that.amount,
                           unit = mass to U2(distance, time))

    @JvmName("minusPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> minus(that: PowerDensityAmount<L, M, T>) =
        PowerDensityAmount(amount = amount - that.amount
                                    * convert(that.mass to mass)
                                    / convert(that.distance to distance,
                                              that.time to time,
                                              that.time to time,
                                              that.time to time),
                           unit = mass to U2(distance, time))

    operator fun minus(that: PowerDensityAmount<L, M, T>) =
        PowerDensityAmount(amount = amount - that.amount,
                           unit = mass to U2(distance, time))

    // endregion

    // region -> Scalar

    @JvmName("divPowerDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: PowerDensityAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount
                              / convert(that.mass to mass)
                              * convert(that.distance to distance,
                                        that.time to time,
                                        that.time to time,
                                        that.time to time),
                     unit = NUMERIC)

    operator fun div(that: PowerDensityAmount<L, M, T>) =
        ScalarAmount(amount = amount / that.amount,
                     unit = NUMERIC)

    // endregion

    // region -> AreaDensity

    @JvmName("divJerk")
    operator fun <L : Unit<Distance>, T : Unit<Time>> div(that: JerkAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount
                                   / convert(that.distance to distance)
                                   * convert(that.time to time,
                                             that.time to time,
                                             that.time to time),
                          unit = mass to distance)

    operator fun div(that: JerkAmount<L, T>) =
        AreaDensityAmount(amount = amount / that.amount,
                          unit = mass to distance)

    // endregion

    // region -> EnergyDensity

    @JvmName("divFrequency")
    operator fun <T : Unit<Time>> div(that: FrequencyAmount<T>) =
        EnergyDensityAmount(amount = amount / that.amount
                                     * convert(that.time to time),
                            unit = mass to U2(distance, time))

    operator fun div(that: FrequencyAmount<T>) =
        EnergyDensityAmount(amount = amount / that.amount,
                            unit = mass to U2(distance, time))

    @JvmName("timesTime")
    operator fun <T : Unit<Time>> times(that: TimeAmount<T>) =
        EnergyDensityAmount(amount = amount * that.amount
                                     * convert(that.time to time),
                            unit = mass to U2(distance, time))

    operator fun times(that: TimeAmount<T>) =
        EnergyDensityAmount(amount = amount * that.amount,
                            unit = mass to U2(distance, time))

    // endregion

    // region -> Frequency

    @JvmName("divEnergyDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: EnergyDensityAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount
                                 / convert(that.mass to mass)
                                 * convert(that.distance to distance,
                                           that.time to time,
                                           that.time to time),
                        unit = NUMERIC to time)

    operator fun div(that: EnergyDensityAmount<L, M, T>) =
        FrequencyAmount(amount = amount / that.amount,
                        unit = NUMERIC to time)

    // endregion

    // region -> FuelEfficiency

    @JvmName("divYank")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, T : Unit<Time>> div(that: YankAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount
                                      / convert(that.distance to distance,
                                                that.mass to mass)
                                      * convert(that.time to time,
                                                that.time to time,
                                                that.time to time),
                             unit = NUMERIC to distance)

    operator fun div(that: YankAmount<L, M, T>) =
        FuelEfficiencyAmount(amount = amount / that.amount,
                             unit = NUMERIC to distance)

    // endregion

    // region -> Intensity

    @JvmName("timesDistance")
    operator fun <L : Unit<Distance>> times(that: DistanceAmount<L>) =
        IntensityAmount(amount = amount * that.amount
                                 * convert(that.distance to distance),
                        unit = mass to time)

    operator fun times(that: DistanceAmount<L>) =
        IntensityAmount(amount = amount * that.amount,
                        unit = mass to time)

    @JvmName("divSpacialFrequency")
    operator fun <L : Unit<Distance>> div(that: SpacialFrequencyAmount<L>) =
        IntensityAmount(amount = amount / that.amount
                                 * convert(that.distance to distance),
                        unit = mass to time)

    operator fun div(that: SpacialFrequencyAmount<L>) =
        IntensityAmount(amount = amount / that.amount,
                        unit = mass to time)

    // endregion

    // region -> Jerk

    @JvmName("divAreaDensity")
    operator fun <L : Unit<Distance>, M : Unit<Mass>> div(that: AreaDensityAmount<L, M>) =
        JerkAmount(amount = amount / that.amount
                            / convert(that.mass to mass)
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = distance to time)

    operator fun div(that: AreaDensityAmount<L, M>) =
        JerkAmount(amount = amount / that.amount,
                   unit = distance to time)

    // endregion

    // region -> MolarEnergy

    @JvmName("divReactionRate")
    operator fun <L : Unit<Distance>, N : Unit<Substance>, T : Unit<Time>> div(that: ReactionRateAmount<L, N, T>) =
        MolarEnergyAmount(amount = amount / that.amount
                                   * convert(that.distance to distance,
                                             that.distance to distance,
                                             that.distance to distance,
                                             that.time to time),
                          unit = U2(distance, mass) to U2(that.substance, time))

    operator fun <N : Unit<Substance>> div(that: ReactionRateAmount<L, N, T>) =
        MolarEnergyAmount(amount = amount / that.amount,
                          unit = U2(distance, mass) to U2(that.substance, time))

    // endregion

    // region -> Power

    @JvmName("timesVolume")
    operator fun <L : Unit<Distance>> times(that: VolumeAmount<L>) =
        PowerAmount(amount = amount * that.amount
                             * convert(that.distance to distance,
                                       that.distance to distance,
                                       that.distance to distance),
                    unit = U2(distance, mass) to time)

    operator fun times(that: VolumeAmount<L>) =
        PowerAmount(amount = amount * that.amount,
                    unit = U2(distance, mass) to time)

    // endregion

    // region -> ReactionRate

    @JvmName("divMolarEnergy")
    operator fun <L : Unit<Distance>, M : Unit<Mass>, N : Unit<Substance>, T : Unit<Time>> div(that: MolarEnergyAmount<L, M, N, T>) =
        ReactionRateAmount(amount = amount / that.amount
                                    / convert(that.distance to distance,
                                              that.distance to distance,
                                              that.mass to mass)
                                    * convert(that.time to time,
                                              that.time to time),
                           unit = that.substance to U2(time, distance))

    operator fun <N : Unit<Substance>> div(that: MolarEnergyAmount<L, M, N, T>) =
        ReactionRateAmount(amount = amount / that.amount,
                           unit = that.substance to U2(time, distance))

    // endregion

    // region -> SpacialFrequency

    @JvmName("divIntensity")
    operator fun <M : Unit<Mass>, T : Unit<Time>> div(that: IntensityAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount
                                        / convert(that.mass to mass)
                                        * convert(that.time to time,
                                                  that.time to time,
                                                  that.time to time),
                               unit = NUMERIC to distance)

    operator fun div(that: IntensityAmount<M, T>) =
        SpacialFrequencyAmount(amount = amount / that.amount,
                               unit = NUMERIC to distance)

    // endregion

    // region -> Yank

    @JvmName("timesArea")
    operator fun <L : Unit<Distance>> times(that: AreaAmount<L>) =
        YankAmount(amount = amount * that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = U2(distance, mass) to time)

    operator fun times(that: AreaAmount<L>) =
        YankAmount(amount = amount * that.amount,
                   unit = U2(distance, mass) to time)

    @JvmName("divFuelEfficiency")
    operator fun <L : Unit<Distance>> div(that: FuelEfficiencyAmount<L>) =
        YankAmount(amount = amount / that.amount
                            * convert(that.distance to distance,
                                      that.distance to distance),
                   unit = U2(distance, mass) to time)

    operator fun div(that: FuelEfficiencyAmount<L>) =
        YankAmount(amount = amount / that.amount,
                   unit = U2(distance, mass) to time)

    // endregion
}
